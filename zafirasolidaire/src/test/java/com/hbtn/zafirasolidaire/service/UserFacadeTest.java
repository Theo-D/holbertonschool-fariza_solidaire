package com.hbtn.zafirasolidaire.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.hbtn.zafirasolidaire.config.SecurityConfigTest;
import com.hbtn.zafirasolidaire.model.User;
import com.hbtn.zafirasolidaire.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserFacade.class, SecurityConfigTest.class})
public class UserFacadeTest {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("unused")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void userFacade_PasswordServiceTest(){
        //Arrange
        String unhashedPass = "UserPass";

        //Act
        String hashedPass = userFacade.encodePassword(unhashedPass);

        //Assert
        assertThat(hashedPass).isNotEqualTo(unhashedPass).isNotNull();
        assertThat(unhashedPass).isNotNull();
        assertThat(userFacade.checkPassword(unhashedPass, hashedPass)).isTrue();

        System.out.println(hashedPass);
        System.out.println(unhashedPass);
    }

    @Test
    public void userFacade_RepositoryServiceTest() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.count()).thenReturn(1L);

        // Act
        Optional<User> foundUser = userFacade.getUserById(userId);
        boolean exists = userFacade.existsById(userId);
        long count = userFacade.countUsers();

        // Assert
        assertThat(foundUser.isPresent()).isTrue();
        assertThat(userId).isEqualTo(foundUser.get().getId());
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
        Exception ex3 = assertThrows(IllegalArgumentException.class, () -> userFacade.saveUser(null));
        assertEquals("User cannot be null.", ex3.getMessage());

        // saveAllUsers with null
        Exception ex4 = assertThrows(IllegalArgumentException.class, () -> userFacade.saveAllUsers(null));
        assertEquals("User list cannot be null or empty.", ex4.getMessage());

        // saveAllUsers with empty list
        Exception ex5 = assertThrows(IllegalArgumentException.class, () -> userFacade.saveAllUsers(Collections.emptyList()));
        assertEquals("User list cannot be null or empty.", ex5.getMessage());
    }
}
