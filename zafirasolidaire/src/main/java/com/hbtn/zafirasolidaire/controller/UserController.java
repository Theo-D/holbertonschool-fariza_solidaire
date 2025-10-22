package com.hbtn.zafirasolidaire.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbtn.zafirasolidaire.dto.RequestPhotoDto;
import com.hbtn.zafirasolidaire.dto.UserDto;
import com.hbtn.zafirasolidaire.dto.UserRequest;
import com.hbtn.zafirasolidaire.model.User;
import com.hbtn.zafirasolidaire.service.UserFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    // ---------- POST ----------//

    // Save a single user
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody @Valid UserRequest userRequest) {
        userFacade.createUser(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
        if (!userFacade.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userFacade.updateUser(id, userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // // Save multiple users
    // @PostMapping("/batch")
    // public ResponseEntity<Void> saveAllUsers(@RequestBody @Valid List<UserRequest> userRequests) {
    //     userFacade.createAllUsers(userRequests);
    //     return ResponseEntity.status(HttpStatus.CREATED).build();
    // }

    // ---------- GET ----------//

    // Get users by list of IDs
    // POST because body needed in request to get Iterable<UserDto>
    // @PostMapping("/batch/ids")
    // public ResponseEntity<Iterable<UserDto>> getAllUsersById(@RequestBody List<UUID> ids) {
    //     Iterable<UserDto> users = userFacade.getAllUsersById(ids);
    //     return ResponseEntity.ok(users);
    // }

    // Get user by ID
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        UserDto userDto = userFacade.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    // Check if user exists by ID
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable UUID id) {
        boolean exists = userFacade.existsById(id);
        return ResponseEntity.ok(exists);
    }

    // Get all users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        Iterable<UserDto> users = userFacade.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{userId}/photo")
    public ResponseEntity<Void> saveEvent(@RequestBody @Valid RequestPhotoDto photoDto, @PathVariable UUID userId) {
        userFacade.addPhoto(photoDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Count users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/count")
    public ResponseEntity<Long> countUsers() {
        long count = userFacade.countUsers();
        return ResponseEntity.ok(count);
    }

    // ---------- DELETE ----------//

    // Delete user by ID
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        userFacade.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    // // Delete a single user (full object)
    // @DeleteMapping
    // public ResponseEntity<Void> deleteUser(@RequestBody User user) {
    //     userFacade.deleteUser(user);
    //     return ResponseEntity.noContent().build();
    // }

    // // Delete all users
    // @DeleteMapping("/all")
    // public ResponseEntity<Void> deleteAllUsers() {
    //     userFacade.deleteAllUsers();
    //     return ResponseEntity.noContent().build();
    // }

    // // Delete specific users
    // @DeleteMapping("/batch")
    // public ResponseEntity<Void> deleteAllUsersFromList(@RequestBody List<User> users) {
    //     userFacade.deleteAllUsers(users);
    //     return ResponseEntity.noContent().build();
    // }
}
