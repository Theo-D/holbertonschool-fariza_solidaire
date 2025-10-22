package com.hbtn.zafirasolidaire.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbtn.zafirasolidaire.dto.RequestPhotoDto;
import com.hbtn.zafirasolidaire.dto.UserDto;
import com.hbtn.zafirasolidaire.dto.UserRequest;
import com.hbtn.zafirasolidaire.mapper.UserMapper;
import com.hbtn.zafirasolidaire.model.User;
import com.hbtn.zafirasolidaire.model.Photo;
import com.hbtn.zafirasolidaire.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserFacade {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PhotoFacade photoFacade;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserFacade(UserRepository userRepository, UserMapper userMapper, PhotoFacade photoFacade, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.userMapper  = userMapper;
        this.photoFacade = photoFacade;
        this.authenticationService = authenticationService;
    }

    //---------- Repository Services ----------//
    public void createUser(UserRequest userRequest) {
        if (userRequest == null) {
            throw new IllegalArgumentException("User request cannot be null.");
        }

        User user = userMapper.userRequestToUser(userRequest);

        user.setPassword(authenticationService.encodePassword(userRequest.getPassword()));
        user.setIsAdmin(false);
        userRepository.save(user);
    }

    public void createAllUsers(Iterable<UserRequest> userRequests) {
        if (userRequests == null || !userRequests.iterator().hasNext()) {
            throw new IllegalArgumentException("User list cannot be null or empty.");
        }

        List<User> users = new ArrayList<>();

        for (UserRequest userRequest : userRequests) {
            User user = userMapper.userRequestToUser(userRequest);
            user.setPassword(authenticationService.encodePassword(userRequest.getPassword()));
            user.setIsAdmin(false);
            users.add(user);
        }

        userRepository.saveAll(users);
    }

    public void addPhoto(RequestPhotoDto requestPhotoDto, UUID userId) {
        if (requestPhotoDto == null) {
            throw new IllegalArgumentException("Photo DTO cannot be null.");
        } else if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }

        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Create and persist Photo
        Photo photo = photoFacade.mapDtoToPhoto(requestPhotoDto);
        photo.setUser(user);
        photoFacade.savePhoto(photo);

        // Link Photo to User
        user.setProfilePic(photo);
        userRepository.save(user);
    }

    public UserDto getUserById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }

        User foundUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return userMapper.userToDto(foundUser);
    }

    //TODO: Maybe implement Dto mapping and return
    public User getUserByEmailAddress(String emailAddress) {
        if (emailAddress == null) {
            throw new IllegalArgumentException("Email address cannot be null.");
        }

        User foundUser = userRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return foundUser;
    }

    public boolean existsById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        return userRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAllWithProfilePic();
        return users.stream()
                    .map(userMapper::userToDto)
                    .collect(Collectors.toList());
    }


    public Iterable<UserDto> getAllUsersById(Iterable<UUID> ids) {
        if (ids == null || !ids.iterator().hasNext()) {
            throw new IllegalArgumentException("ID list cannot be null or empty.");
        }

        return mapToDto(userRepository.findAllById(ids));
    }

    @Transactional
    public void deleteUserById(UUID userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Unlink the photo
        user.setProfilePic(null);
        userRepository.save(user); // important!

        userRepository.delete(user);
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

    //--------- Helper methods ---------//

    private Iterable<UserDto> mapToDto(Iterable<User> users) {
        return StreamSupport.stream(users.spliterator(), false)
            .map(userMapper::userToDto)
            .collect(Collectors.toList());
    }

}
