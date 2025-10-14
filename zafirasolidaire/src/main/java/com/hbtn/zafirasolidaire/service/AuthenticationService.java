package com.hbtn.zafirasolidaire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hbtn.zafirasolidaire.model.User;

@Service
public class AuthenticationService {

    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationService(PasswordEncoder encoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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
