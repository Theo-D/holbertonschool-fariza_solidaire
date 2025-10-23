package com.hbtn.zafirasolidaire.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hbtn.zafirasolidaire.model.Photo;
import com.hbtn.zafirasolidaire.model.User;
import com.hbtn.zafirasolidaire.repository.UserRepository;

@Component
public class UsersInitilizer implements CommandLineRunner {

    private final UserRepository userRepository;

    public UsersInitilizer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        // Define 12 fake users
        List<User> mockUsers = List.of(
                new User().setEmailAddress("alice.smith@example.com").setFirstName("Alice").setLastName("Smith")
                        .setProfilePic(new Photo().setUrl("https://randomuser.me/api/portraits/women/73.jpg")),
                new User().setEmailAddress("bob.johnson@example.com").setFirstName("Bob").setLastName("Johnson")
                        .setProfilePic(new Photo().setUrl("https://randomuser.me/api/portraits/men/12.jpg")),
                new User().setEmailAddress("carla.williams@example.com").setFirstName("Carla").setLastName("Williams")
                        .setProfilePic(new Photo().setUrl("https://randomuser.me/api/portraits/women/54.jpg")),
                new User().setEmailAddress("david.brown@example.com").setFirstName("David").setLastName("Brown")
                        .setProfilePic(new Photo().setUrl("https://randomuser.me/api/portraits/men/21.jpg")),
                new User().setEmailAddress("emma.jones@example.com").setFirstName("Emma").setLastName("Jones")
                        .setProfilePic(new Photo().setUrl("https://randomuser.me/api/portraits/women/25.jpg")),
                new User().setEmailAddress("frank.miller@example.com").setFirstName("Frank").setLastName("Miller")
                        .setProfilePic(new Photo().setUrl("https://randomuser.me/api/portraits/men/53.jpg")),
                new User().setEmailAddress("grace.davis@example.com").setFirstName("Grace").setLastName("Davis")
                        .setProfilePic(new Photo().setUrl("https://randomuser.me/api/portraits/women/63.jpg")),
                new User().setEmailAddress("henry.garcia@example.com").setFirstName("Henry").setLastName("Garcia")
                        .setProfilePic(new Photo().setUrl("https://randomuser.me/api/portraits/men/45.jpg")),
                new User().setEmailAddress("isla.martinez@example.com").setFirstName("Isla").setLastName("Martinez")
                        .setProfilePic(new Photo().setUrl("https://randomuser.me/api/portraits/women/65.jpg")),
                new User().setEmailAddress("jack.rodriguez@example.com").setFirstName("Jack").setLastName("Rodriguez")
                        .setProfilePic(new Photo().setUrl("https://randomuser.me/api/portraits/men/75.jpg")),
                new User().setEmailAddress("kate.lewis@example.com").setFirstName("Kate").setLastName("Lewis")
                        .setProfilePic(new Photo().setUrl("https://randomuser.me/api/portraits/women/11.jpg")),
                new User().setEmailAddress("liam.lee@example.com").setFirstName("Liam").setLastName("Lee")
                        .setProfilePic(new Photo().setUrl("https://randomuser.me/api/portraits/men/15.jpg")));

        // Shared hashed password (e.g., "password123")
        String hashedPassword = "$2a$16$gykRWmAyTRCOZdgdQSdzFu7XiIt7Mn/eW3HNdYJMD.7CwA5eQR/fq";

        for (User user : mockUsers) {
            if (userRepository.findByEmailAddress(user.getEmailAddress()).isEmpty()) {
                user.setPassword(hashedPassword);
                user.setIsAdmin(false);
                userRepository.save(user);
            }
        }
    }
}
