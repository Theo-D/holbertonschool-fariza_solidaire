package com.hbtn.zafirasolidaire.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.hbtn.zafirasolidaire.config.ConfigTest;
import com.hbtn.zafirasolidaire.dto.UserDto;
import com.hbtn.zafirasolidaire.dto.UserRequest;
import com.hbtn.zafirasolidaire.mapper.UserMapper;
import com.hbtn.zafirasolidaire.model.User;
import com.hbtn.zafirasolidaire.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserFacade.class, ConfigTest.class})
public class UserFacadeTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private UserFacade userFacade;

    @Test
    public void userFacade_PasswordServiceTest() {
        // Arrange
        String unhashedPass = "UserPass";
        String hashedPassExpected = "encoded_UserPass";

        when(passwordEncoder.encode(unhashedPass)).thenReturn(hashedPassExpected);
        when(passwordEncoder.matches(unhashedPass, hashedPassExpected)).thenReturn(true);

        // Act
        String hashedPass = authenticationService.encodePassword(unhashedPass);

        // Assert
        assertThat(hashedPass).isNotEqualTo(unhashedPass).isNotNull();
        assertThat(authenticationService.checkPassword(unhashedPass, hashedPass)).isTrue();
    }


    @Test
    public void userFacade_RepositoryServiceTest() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User();
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        user.setId(userId);

        when(userMapper.userToDto(user)).thenReturn(userDto);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.count()).thenReturn(1L);

        // Act
        UserDto foundUser = userFacade.getUserById(userId);
        boolean exists = userFacade.existsById(userId);
        long count = userFacade.countUsers();

        // Assert
        assertThat(foundUser).isNotNull();
        assertThat(userId).isEqualTo(foundUser.getUserId());
        assertThat(exists);
        assertThat(1L).isEqualTo(count);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).count();
    }

    @Test
    public void userFacade_RepositoryServiceTest_Exceptions() {
        // getUserById with null
        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> userFacade.getUserById(null));
        assertEquals("User ID cannot be null.", ex1.getMessage());

        // existsById with null
        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> userFacade.existsById(null));
        assertEquals("User ID cannot be null.", ex2.getMessage());

        // saveUser with null
        Exception ex3 = assertThrows(IllegalArgumentException.class, () -> userFacade.createUser(null));
        assertEquals("User request cannot be null.", ex3.getMessage());

        // saveAllUsers with null
        Exception ex4 = assertThrows(IllegalArgumentException.class, () -> userFacade.createAllUsers(null));
        assertEquals("User list cannot be null or empty.", ex4.getMessage());

        // saveAllUsers with empty list
        Exception ex5 = assertThrows(IllegalArgumentException.class, () -> userFacade.createAllUsers(Collections.emptyList()));
        assertEquals("User list cannot be null or empty.", ex5.getMessage());
    }

    @Test
    public void userFacade_getAllUsers_shouldReturnUserDtos() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);

        UserDto userDto = new UserDto();
        userDto.setUserId(userId);

        List<User> userList = List.of(user);

        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.userToDto(user)).thenReturn(userDto);

        // Act
        Iterable<UserDto> result = userFacade.getAllUsers();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result).containsExactly(userDto);

        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).userToDto(user);
    }

    @Test
    public void userFacade_getAllUsersById_shouldReturnUserDtos() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);

        UserDto userDto = new UserDto();
        userDto.setUserId(userId);

        List<UUID> userIds = List.of(userId);
        List<User> users = List.of(user);

        when(userRepository.findAllById(userIds)).thenReturn(users);
        when(userMapper.userToDto(user)).thenReturn(userDto);

        // Act
        Iterable<UserDto> result = userFacade.getAllUsersById(userIds);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result).containsExactly(userDto);

        verify(userRepository, times(1)).findAllById(userIds);
        verify(userMapper, times(1)).userToDto(user);
    }

    @Test
    public void userFacade_getAllUsersById_shouldThrowWhenInputIsNullOrEmpty() {
        // Null input
        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> userFacade.getAllUsersById(null));
        assertEquals("ID list cannot be null or empty.", ex1.getMessage());

        // Empty input
        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> userFacade.getAllUsersById(Collections.emptyList()));
        assertEquals("ID list cannot be null or empty.", ex2.getMessage());
    }

    @Test
    public void userFacade_createUser_shouldMapEncodeAndSave() {
        // Arrange
        UserRequest request = new UserRequest();
        request.setFirstName("Test");
        request.setLastName("User");
        request.setEmailAddress("test@example.com");
        request.setPassword("plainPassword");

        User mappedUser = new User();
        when(userMapper.userRequestToUser(request)).thenReturn(mappedUser);
        when(passwordEncoder.encode("plainPassword")).thenReturn("hashedPassword");

        // Act
        userFacade.createUser(request);

        // Assert
        assertThat(mappedUser.getPassword()).isEqualTo("hashedPassword");
        assertThat(mappedUser.getIsAdmin()).isFalse();

        verify(userMapper).userRequestToUser(request);
        verify(passwordEncoder).encode("plainPassword");
        verify(userRepository).save(mappedUser);
    }

    @Test
    public void userFacade_createAllUsers_shouldMapEncodeAndSaveAll() {
        // Arrange
        UserRequest request1 = new UserRequest();
        request1.setPassword("pass1");
        UserRequest request2 = new UserRequest();
        request2.setPassword("pass2");

        User user1 = new User();
        User user2 = new User();

        List<UserRequest> requests = List.of(request1, request2);

        when(userMapper.userRequestToUser(request1)).thenReturn(user1);
        when(userMapper.userRequestToUser(request2)).thenReturn(user2);
        when(passwordEncoder.encode("pass1")).thenReturn("hashed1");
        when(passwordEncoder.encode("pass2")).thenReturn("hashed2");

        // Act
        userFacade.createAllUsers(requests);

        // Assert
        assertThat(user1.getPassword()).isEqualTo("hashed1");
        assertThat(user2.getPassword()).isEqualTo("hashed2");
        assertThat(user1.getIsAdmin()).isFalse();
        assertThat(user2.getIsAdmin()).isFalse();

        verify(userMapper).userRequestToUser(request1);
        verify(userMapper).userRequestToUser(request2);
        verify(passwordEncoder).encode("pass1");
        verify(passwordEncoder).encode("pass2");
        verify(userRepository).saveAll(List.of(user1, user2));
    }

    @Test
    public void userFacade_createUser_shouldThrowOnNullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userFacade.createUser(null));
        assertEquals("User request cannot be null.", exception.getMessage());
    }

    @Test
    public void userFacade_createAllUsers_shouldThrowOnNullOrEmpty() {
        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> userFacade.createAllUsers(null));
        assertEquals("User list cannot be null or empty.", ex1.getMessage());

        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> userFacade.createAllUsers(Collections.emptyList()));
        assertEquals("User list cannot be null or empty.", ex2.getMessage());
    }

}
