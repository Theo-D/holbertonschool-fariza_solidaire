package com.hbtn.zafirasolidaire.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.hbtn.zafirasolidaire.config.SecurityConfigTest;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserService.class, SecurityConfigTest.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void userServiceTest(){
        //Arrange
        String unhashedPass = "UserPass";


        //Act
        String hashedPass = userService.encodePassword(unhashedPass);

        //Assert
        assertThat(hashedPass).isNotEqualTo(unhashedPass).isNotNull();
        assertThat(unhashedPass).isNotNull();
        assertThat(userService.checkPassword(unhashedPass, hashedPass)).isTrue();

        System.out.println(hashedPass);
        System.out.println(unhashedPass);
    }
}
