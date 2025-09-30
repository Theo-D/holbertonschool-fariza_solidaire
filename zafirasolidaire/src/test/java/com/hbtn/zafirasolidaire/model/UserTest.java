package com.hbtn.zafirasolidaire.model;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class UserTest {
    static class BaseUserTest extends User{

        public BaseUserTest setId(UUID id) {
            super.id = id;
            return this;
        }
    }

    @Test
    public void userModelCreationTest(){
        //Arrange

        BaseUserTest baseUserTest = new BaseUserTest();
        Photo photo = new Photo();

        //Act

        //Has to be called manually for testing purpose
        //@PrePersist only work on JPA repository modifications
        baseUserTest.onCreate();

        baseUserTest.setId(UUID.randomUUID())
                    .setFirstName("Billy")
                    .setLastName("Bob")
                    .setEmail("billybob@gmail.com")
                    .setProfilePic(photo)
                    .setAdmin(true)
                    .setPassword("UserPass");

        baseUserTest.onUpdate();

        //Assert

        assertThat(baseUserTest.getId()).isNotNull().isInstanceOf(UUID.class);
        assertThat(baseUserTest.getCreateDate()).isNotNull().isInstanceOf(Date.class);
        assertThat(baseUserTest.getUpdateDate()).isNotNull().isAfter(baseUserTest.getCreateDate()).isInstanceOf(Date.class);
        assertThat(baseUserTest.getFirstName()).isNotNull().isInstanceOf(String.class);
        assertThat(baseUserTest.getLastName()).isNotNull().isInstanceOf(String.class);
        assertThat(baseUserTest.getEmail()).isNotNull().isInstanceOf(String.class);
        assertThat(baseUserTest.getProfilePic()).isNotNull().isInstanceOf(Photo.class);
        assertThat(baseUserTest.isAdmin()).isNotNull().isInstanceOf(Boolean.class);
        //assertThat(baseUserTest.getPasswordForTestingOnly()).isNotNull().isInstanceOf(String.class); Test passed

        System.out.println(baseUserTest.getId());
        System.out.println(baseUserTest.getFirstName());
        System.out.println(baseUserTest.getLastName());
        System.out.println(baseUserTest.getEmail());
        System.out.println(baseUserTest.getProfilePic());
        System.out.println(baseUserTest.isAdmin());
        //System.out.println(baseUserTest.getPasswordForTestingOnly()); Test passed
    }
}
