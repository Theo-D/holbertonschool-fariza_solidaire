package com.hbtn.zafirasolidaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbtn.zafirasolidaire.dto.UserLoginDto;
import com.hbtn.zafirasolidaire.dto.UserRequest;
import com.hbtn.zafirasolidaire.model.User;
import com.hbtn.zafirasolidaire.service.AuthenticationService;
import com.hbtn.zafirasolidaire.service.UserFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserFacade userFacade;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(UserFacade userFacade, AuthenticationService authenticationService) {
        this.userFacade = userFacade;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid UserRequest userRequest) {
        userFacade.createUser(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
        User user = userFacade.getUserByEmailAddress(userLoginDto.getEmailAddress());

        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.verifyUser(user, userLoginDto.getPassword()));
    }
}
