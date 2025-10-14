package com.hbtn.zafirasolidaire.config;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hbtn.zafirasolidaire.model.User;
import com.hbtn.zafirasolidaire.repository.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final String adminEmail = "admin@zafira.com";

    private UserRepository userRepository;

    public AdminInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        Optional<User> admin = userRepository.findByEmailAddress(adminEmail);
        if (!admin.isPresent()) {
            User newAdmin = new User().setEmailAddress(adminEmail)
                                      .setFirstName("Admin")
                                      .setLastName("Admin")
                                      .setPassword("$2a$10$SwTJTi4SgyinjjLo3rbUreEV8l6qPz5t13ykp.ox0yGoo7qgJ28k2")
                                      .setIsAdmin(true);
            userRepository.save(newAdmin);
        }
    }

}
