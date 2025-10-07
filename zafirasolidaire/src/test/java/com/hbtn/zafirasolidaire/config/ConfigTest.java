package com.hbtn.zafirasolidaire.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hbtn.zafirasolidaire.mapper.UserMapper;
import com.hbtn.zafirasolidaire.repository.UserRepository;

@TestConfiguration
public class ConfigTest {
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public UserMapper userMapper() {
        return Mockito.mock(UserMapper.class);
    }

    //Removed SecurityFilterChain and HttpSecurity import for testing purpose
}
