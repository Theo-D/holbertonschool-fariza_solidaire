package com.hbtn.zafirasolidaire.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbtn.zafirasolidaire.dto.UserLoginDto;
import com.hbtn.zafirasolidaire.dto.UserRequest;
import com.hbtn.zafirasolidaire.model.CustomUserDetails;
import com.hbtn.zafirasolidaire.model.RefreshToken;
import com.hbtn.zafirasolidaire.model.User;
import com.hbtn.zafirasolidaire.repository.RefreshTokenRepository;
import com.hbtn.zafirasolidaire.service.AuthenticationService;
import com.hbtn.zafirasolidaire.service.UserFacade;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserFacade userFacade;
    private final AuthenticationService authenticationService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public AuthenticationController(UserFacade userFacade, AuthenticationService authenticationService, RefreshTokenRepository refreshTokenRepository) {
        this.userFacade = userFacade;
        this.authenticationService = authenticationService;
        this.refreshTokenRepository = refreshTokenRepository;
    }



    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid UserRequest userRequest) {

        userFacade.createUser(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {
        User user = userFacade.getUserByEmailAddress(userLoginDto.getEmailAddress());

        //access token
        String jwtToken = authenticationService.verifyUser(user, userLoginDto.getPassword());

        //refresh token
        RefreshToken refreshToken = authenticationService.createRefreshToken(user);

        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken.getToken());

        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge((int) Duration.between(Instant.now(), refreshToken.getExpirationDate())
                                                                                       .getSeconds());

        response.addCookie(refreshTokenCookie);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("accessToken", jwtToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(value = "refreshToken", required = false) String refreshToken,
                                    HttpServletResponse response) {

        if (refreshToken != null) {
            refreshTokenRepository.findByToken(refreshToken)
                    .ifPresent(refreshTokenRepository::delete);
        }

        // Overwrite cookie with empty value to delete it
        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());
        return ResponseEntity.ok(Map.of("message", "Successfully logged out"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(
        @CookieValue(value = "refreshToken", required = false) String oldRefreshToken,
        HttpServletResponse response) {

        if (oldRefreshToken == null || oldRefreshToken.isBlank()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Missing refresh token. Please log in again."));
        }

        try {
            Map<String, String> tokens = authenticationService.refresh(oldRefreshToken);

            ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", tokens.get("refreshToken"))
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(7 * 24 * 60 * 60)
                    .sameSite("Strict")
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

            return ResponseEntity.ok(Map.of("accessToken", tokens.get("accessToken")));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> getUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        Map<String, String> response = new HashMap<>();
        response.put("id", String.valueOf(userDetails.getId()));
        response.put("email", userDetails.getUsername());
        response.put("role", role);

        return ResponseEntity.ok(response);
    }

}
