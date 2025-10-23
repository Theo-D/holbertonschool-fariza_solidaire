package com.hbtn.zafirasolidaire.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbtn.zafirasolidaire.dto.RequestServicedUserDto;
import com.hbtn.zafirasolidaire.dto.ServicedUserDto;
import com.hbtn.zafirasolidaire.service.ServicedUserFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("serviced_users")
public class ServicedUserController {
    private final ServicedUserFacade servicedUserFacade;

    @Autowired
    public ServicedUserController(ServicedUserFacade servicedUserFacade) {
        this.servicedUserFacade = servicedUserFacade;
    }

    // ---------- POST ----------//

    // Save a single servicedUser
    @PostMapping
    public ResponseEntity<Void> saveServicedUser(@RequestBody @Valid RequestServicedUserDto servicedUserDto) {
        servicedUserFacade.createServicedUser(servicedUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // // Save multiple servicedUsers
    // @PostMapping("/batch")
    // public ResponseEntity<Void> saveAllServicedUsers(@RequestBody @Valid
    // List<ServicedUserDto> servicedUserDtos) {
    // servicedUserFacade.createAllServicedUsers(servicedUserDtos);
    // return ResponseEntity.status(HttpStatus.CREATED).build();
    // }

    // Get servicedUser by ID
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ServicedUserDto> getServicedUserById(@PathVariable UUID id) {
        ServicedUserDto servicedUserDto = servicedUserFacade.getServicedUserById(id);
        return ResponseEntity.ok(servicedUserDto);
    }

    // Check if servicedUser exists by ID
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable UUID id) {
        boolean exists = servicedUserFacade.existsById(id);
        return ResponseEntity.ok(exists);
    }

    // Get all servicedUsers
    @GetMapping
    public ResponseEntity<Iterable<ServicedUserDto>> getAllServicedUsers() {
        Iterable<ServicedUserDto> servicedUsers = servicedUserFacade.getAllServicedUsers();
        return ResponseEntity.ok(servicedUsers);
    }

    // Count servicedUsers
    @GetMapping("/count")
    public ResponseEntity<Long> countServicedUsers() {
        long count = servicedUserFacade.countServicedUsers();
        return ResponseEntity.ok(count);
    }

    // ---------- DELETE ----------//

    // Delete servicedUser by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicedUserById(@PathVariable UUID id) {
        servicedUserFacade.deleteServicedUserById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-user-id/{userId}")
    public ResponseEntity<Void> deleteServicedUserByUserId(@PathVariable UUID userId) {
        servicedUserFacade.deleteServicedUserByUserId(userId);
        return ResponseEntity.noContent().build();
    }

    // // Delete a single servicedUser (full object)
    // @DeleteMapping
    // public ResponseEntity<Void> deleteServicedUser(@RequestBody ServicedUser
    // servicedUser) {
    // servicedUserFacade.deleteServicedUser(servicedUser);
    // return ResponseEntity.noContent().build();
    // }

    // // Delete all servicedUsers
    // @DeleteMapping("/all")
    // public ResponseEntity<Void> deleteAllServicedUsers() {
    // servicedUserFacade.deleteAllServicedUsers();
    // return ResponseEntity.noContent().build();
    // }

    // // Delete specific servicedUsers
    // @DeleteMapping("/batch")
    // public ResponseEntity<Void> deleteAllServicedUsersFromlist(@RequestBody
    // List<ServicedUser> servicedUsers) {
    // servicedUserFacade.deleteAllServicedUsers(servicedUsers);
    // return ResponseEntity.noContent().build();
    // }
}
