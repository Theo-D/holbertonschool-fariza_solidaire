package com.hbtn.zafirasolidaire.service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hbtn.zafirasolidaire.model.RefreshToken;
import com.hbtn.zafirasolidaire.model.User;
import com.hbtn.zafirasolidaire.repository.RefreshTokenRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthenticationService {

    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refreshTokenExpirationMs}")
    private long refreshTokenDuration;

    @Autowired
    public AuthenticationService(PasswordEncoder encoder, JwtService jwtService, AuthenticationManager authenticationManager,RefreshTokenRepository refreshTokenRepository) {
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.refreshTokenRepository = refreshTokenRepository;
    }



    @Transactional
    public Map<String, String> refresh(String oldRefreshToken) {
        RefreshToken existingToken = refreshTokenRepository.findByToken(oldRefreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (existingToken.getExpirationDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(existingToken);
            throw new RuntimeException("Refresh token expired");
        }

        User user = existingToken.getUser();

        String newAccessToken = jwtService.generateToken(user.getId(), user.getIsAdmin());

        // ✅ call the specialized method here
        RefreshToken newRefreshToken = rotateRefreshToken(user, existingToken);

        return Map.of(
                "accessToken", newAccessToken,
                "refreshToken", newRefreshToken.getToken()
        );
    }

    @Transactional
    public RefreshToken createRefreshToken(User user) {
        refreshTokenRepository.deleteByUserId(user.getId());
        String tokenValue = jwtService.generateRefreshTokenValue();

        RefreshToken refreshToken = new RefreshToken()
                .setUser(user)
                .setToken(tokenValue)
                .setExpirationDate(Instant.now().plusMillis(refreshTokenDuration));

        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    private RefreshToken rotateRefreshToken(User user, RefreshToken existingToken) {
        // Update the existing token in place (preferred)
        String newTokenValue = jwtService.generateRefreshTokenValue();
        existingToken.setToken(newTokenValue);
        existingToken.setExpirationDate(Instant.now().plusMillis(refreshTokenDuration));
        return refreshTokenRepository.save(existingToken);
    }

    @Transactional
    public void logout(UUID userId) {
        // Remove all refresh tokens linked to the user (or just one if you only allow one)
        refreshTokenRepository.deleteByUserId(userId);
    }


    public String encodePassword(String rawPassword) {
            return encoder.encode(rawPassword);
        }

        public boolean checkPassword(String rawPassword, String hashedPassword) {
            return encoder.matches(rawPassword, hashedPassword);
        }

        public String verifyUser(User user, String rawPassword) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailAddress(),
                                                                                       rawPassword));
            return jwtService.generateToken(user.getId(), user.getIsAdmin());
        }
}
