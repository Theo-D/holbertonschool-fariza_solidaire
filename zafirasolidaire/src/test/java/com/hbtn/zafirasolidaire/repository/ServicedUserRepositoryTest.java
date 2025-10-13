package com.hbtn.zafirasolidaire.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.hbtn.zafirasolidaire.config.JpaConfigTest;
import com.hbtn.zafirasolidaire.model.ServicedUser;
import com.hbtn.zafirasolidaire.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import(JpaConfigTest.class)
public class ServicedUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicedUserRepository servicedUserRepository;

    private User createValidUser() {
        return new User()
            .setFirstName("John")
            .setLastName("Doe")
            .setEmailAddress("john.doe@example.com")
            .setPassword("securePassword123")
            .setIsAdmin(false)
            .setIsServiced(true);
    }

    @Test
    void testSaveValidServicedUser() {
        // Arrange
        User user = userRepository.save(createValidUser());

        ServicedUser servicedUser = new ServicedUser()
            .setUser(user);

        // Act
        ServicedUser saved = servicedUserRepository.save(servicedUser);

        // Assert
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isEqualTo(servicedUser.getId());
        assertThat(saved.getUser()).isNotNull();
        assertThat(saved.getUser().getId()).isEqualTo(user.getId());
        assertThat(saved.getUser().getEmailAddress()).isEqualTo("john.doe@example.com");

        System.out.println("Saved ServicedUser: " + saved);
    }
}
