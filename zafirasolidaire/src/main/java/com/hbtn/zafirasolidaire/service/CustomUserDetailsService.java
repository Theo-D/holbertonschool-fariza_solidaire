package com.hbtn.zafirasolidaire.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hbtn.zafirasolidaire.model.CustomUserDetails;
import com.hbtn.zafirasolidaire.model.User;
import com.hbtn.zafirasolidaire.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return new CustomUserDetails(user);
    }

}
