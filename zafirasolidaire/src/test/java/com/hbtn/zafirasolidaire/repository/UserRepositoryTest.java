package com.hbtn.zafirasolidaire.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hbtn.zafirasolidaire.config.SecurityConfigTest;
import com.hbtn.zafirasolidaire.model.Photo;
import com.hbtn.zafirasolidaire.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import(SecurityConfigTest.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void userRepository_userCreationTest() {

        //Arrange
        User user1 = new User();
        User user2 = new User();
        Photo photo1 = new Photo();
        Photo photo2 = new Photo();

        photo1.setUrl("img/billyProfilePic");
        photo2.setUrl("img/marcelProfilePic");

        user1.setFirstName("Billy")
             .setLastName("Bob")
             .setEmail("billybob@gmail.com")
             .setProfilePic(photo1)
             .setIsAdmin(false)
             .setPassword(passwordEncoder.encode("BillyPass"));

        user2.setFirstName("Marcel")
             .setLastName("Vincent")
             .setEmail("m.vincent@hotmail.fr")
             .setProfilePic(photo2)
             .setIsAdmin(true)
             .setPassword(passwordEncoder.encode("MarcelPass"));

        //Act
        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);

        Iterable<User> userList = userRepository.findAll();

        //Assert
        assertThat(savedUser1).isNotNull();
        assertThat(savedUser2).isNotNull();
        assertThat(savedUser1.getId()).isNotNull();
        assertThat(userList).isNotEmpty().isNotNull();

        System.out.println("Saved user 1: " + savedUser1);
        System.out.println("Saved user 2: " + savedUser2);
        System.out.println("Users list: " + userList);

        for (User user : userList) {
            System.out.println(user);
        }

    }

    @Test
    public void userRepository_userReadTest() {

        //Arrange
        User user1 = new User();
        User user2 = new User();
        Photo photo1 = new Photo();
        Photo photo2 = new Photo();

        photo1.setUrl("img/billyProfilePic");
        photo2.setUrl("img/marcelProfilePic");

        user1.setFirstName("Billy")
             .setLastName("Bob")
             .setEmail("billybob@gmail.com")
             .setProfilePic(photo1)
             .setIsAdmin(false)
             .setPassword(passwordEncoder.encode("BillyPass"));

        user2.setFirstName("Marcel")
             .setLastName("Vincent")
             .setEmail("m.vincent@hotmail.fr")
             .setProfilePic(photo2)
             .setIsAdmin(true)
             .setPassword(passwordEncoder.encode("MarcelPass"));

        //Act
        userRepository.save(user1);
        userRepository.save(user2);

        Optional<User> foundUser = userRepository.findById(user1.getId());
        Optional<User> idLessUser = userRepository.findById(UUID.randomUUID());
        Iterable<User> userList = userRepository.findAll();

        //Assert
        assertThat(foundUser).isNotNull();
        assertThat(idLessUser).isEmpty();
        assertThat(userList).isNotNull().isNotEmpty();

        System.out.println("Found user: " + foundUser);
        System.out.println("User list: " + userList);
    }

    @Test
    public void userRepository_userUpdateTest() {

        //Arrange
        User user1 = new User();
        Photo photo1 = new Photo();

        photo1.setUrl("img/billyProfilePic");

        user1.setFirstName("Billy")
             .setLastName("Bob")
             .setEmail("billybob@gmail.com")
             .setProfilePic(photo1)
             .setIsAdmin(false)
             .setPassword(passwordEncoder.encode("BillyPass"));


        //Act

        User savedUser1 = userRepository.save(user1);

        //Deep copy of savedUser1 to be compared to updatedUser1
        savedUser1 = new User().setId(savedUser1.getId())
                               .setFirstName(savedUser1.getFirstName())
                               .setLastName(savedUser1.getLastName())
                               .setEmail(savedUser1.getEmail())
                               .setProfilePic(savedUser1.getProfilePic())
                               .setPassword(passwordEncoder.encode("MarcelPass"));

        User foundUser1 = userRepository.findById(user1.getId()).orElseThrow();

        foundUser1.setFirstName("Timothée");

        User updatedUser1 = userRepository.save(foundUser1);


        //Assert
        assertThat(savedUser1.getFirstName()).isNotEqualTo(updatedUser1.getFirstName());

        System.out.println("Saved User1 First Name: " + savedUser1.getFirstName());
        System.out.println("Updated User1 First Name: " + updatedUser1.getFirstName());


    }

    @Test
    public void userRepository_userDeleteTest() {

        //Arrange
        User user1 = new User();
        User user2 = new User();
        Photo photo1 = new Photo();
        Photo photo2 = new Photo();

        photo1.setUrl("img/billyProfilePic");
        photo2.setUrl("img/marcelProfilePic");

        user1.setFirstName("Billy")
             .setLastName("Bob")
             .setEmail("billybob@gmail.com")
             .setProfilePic(photo1)
             .setIsAdmin(false)
             .setPassword(passwordEncoder.encode("BillyPass"));

        user2.setFirstName("Marcel")
             .setLastName("Vincent")
             .setEmail("m.vincent@hotmail.fr")
             .setProfilePic(photo2)
             .setIsAdmin(true)
             .setPassword(passwordEncoder.encode("MarcelPass"));

        //Act
        userRepository.save(user1);
        userRepository.save(user2);

        userRepository.delete(user1);
        userRepository.delete(user2);

        System.out.println("Etat de Photo1: " + photo1);
        System.out.println("Etat de Photo2: " + photo2);

        //Assert
        assertThat(userRepository.findAll()).isEmpty();

    }
}
