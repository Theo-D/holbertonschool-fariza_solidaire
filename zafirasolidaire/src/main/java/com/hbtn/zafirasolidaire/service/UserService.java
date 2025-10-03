package com.hbtn.zafirasolidaire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//TODO: CREATE USER DTO SERVICE
@Service
public class UserService {

    private final PasswordEncoder encoder;

    @Autowired
    public UserService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }



    public String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public boolean checkPassword(String rawPassord, String hashedPassword) {
        return encoder.matches(rawPassord, hashedPassword);
    }

}
