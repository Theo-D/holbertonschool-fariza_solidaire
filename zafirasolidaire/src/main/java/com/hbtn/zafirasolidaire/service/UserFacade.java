package com.hbtn.zafirasolidaire.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hbtn.zafirasolidaire.model.User;
import com.hbtn.zafirasolidaire.repository.UserRepository;

//TODO: CREATE USER DTO SERVICE
@Service
public class UserFacade {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Autowired
    public UserFacade(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    //---------- Password Services ----------//
    public String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public boolean checkPassword(String rawPassord, String hashedPassword) {
        return encoder.matches(rawPassord, hashedPassword);
    }

    //---------- Repository Services ----------//
    public void saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        userRepository.save(user);
    }

    public void saveAllUsers(Iterable<User> users) {
        if (users == null || !users.iterator().hasNext()) {
            throw new IllegalArgumentException("User list cannot be null or empty.");
        }
        userRepository.saveAll(users);
    }

    public Optional<User> getUserById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        return userRepository.findById(id);
    }

    public boolean existsById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        return userRepository.existsById(id);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Iterable<User> getAllUsersById(Iterable<UUID> ids) {
        if (ids == null || !ids.iterator().hasNext()) {
            throw new IllegalArgumentException("ID list cannot be null or empty.");
        }
        return userRepository.findAllById(ids);
    }

    public void deleteUserById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        userRepository.deleteById(id);
    }

    public void deleteUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        userRepository.delete(user);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void deleteAllUsers(Iterable<User> users) {
        if (users == null || !users.iterator().hasNext()) {
            throw new IllegalArgumentException("User list cannot be null or empty.");
        }
        userRepository.deleteAll(users);
    }

    public long countUsers() {
        return userRepository.count();
    }

}
