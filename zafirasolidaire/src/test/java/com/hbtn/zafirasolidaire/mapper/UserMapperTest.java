package com.hbtn.zafirasolidaire.mapper;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hbtn.zafirasolidaire.config.ConfigTest;
import com.hbtn.zafirasolidaire.dto.UserDto;
import com.hbtn.zafirasolidaire.model.Photo;
import com.hbtn.zafirasolidaire.model.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigTest.class)
public class UserMapperTest {

        private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

        @Autowired
        PasswordEncoder passwordEncoder;


        @Test
        void testToDto_UserMappedToDto() {

            //Arrange
            Photo photo = new Photo();
            photo.setUrl("/img/userPhoto.jpg");

            User user = new User().setId(UUID.randomUUID())
                                  .setFirstName("Billy")
                                  .setLastName("Bob")
                                  .setEmailAddress("billybob@gmail.com")
                                  .setProfilePic(photo)
                                  .setPassword(passwordEncoder.encode("BillyPass"))
                                  .setIsAdmin(false);

            //Act
            UserDto userDto = userMapper.userToDto(user);

            //Assert
            assertThat(userDto).isNotNull();
            assertThat(user.getId()).isEqualTo(userDto.getUserId());
            assertThat(user.getFirstName()).isEqualTo(userDto.getFirstName());
            assertThat(user.getProfilePic().getUrl()).isEqualTo(userDto.getPhotoUrl());

            System.out.println("User DTO: " + userDto);
            System.out.println("User: " + user);
        }
}
