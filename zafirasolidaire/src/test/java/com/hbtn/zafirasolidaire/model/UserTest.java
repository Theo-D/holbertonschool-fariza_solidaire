package com.hbtn.zafirasolidaire.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class UserTest {
    static class BaseUserTest extends User{

        public BaseUserTest setId(UUID id) {
            super.id = id;
            return this;
        }
    }

    private Validator validator;

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
                    .setEmailAddress("billybob@gmail.com")
                    .setProfilePic(photo)
                    .setIsAdmin(true)
                    .setPassword("UserPass");

        baseUserTest.onUpdate();

        //Assert

        assertThat(baseUserTest.getId()).isNotNull().isInstanceOf(UUID.class);
        assertThat(baseUserTest.getCreateDate()).isNotNull().isInstanceOf(Date.class);
        assertThat(baseUserTest.getUpdateDate()).isNotNull().isAfter(baseUserTest.getCreateDate()).isInstanceOf(Date.class);
        assertThat(baseUserTest.getFirstName()).isNotNull().isInstanceOf(String.class);
        assertThat(baseUserTest.getLastName()).isNotNull().isInstanceOf(String.class);
        assertThat(baseUserTest.getEmailAddress()).isNotNull().isInstanceOf(String.class);
        assertThat(baseUserTest.getProfilePic()).isNotNull().isInstanceOf(Photo.class);
        assertThat(baseUserTest.getIsAdmin()).isNotNull().isInstanceOf(Boolean.class);
        //assertThat(baseUserTest.getPasswordForTestingOnly()).isNotNull().isInstanceOf(String.class); Test passed

        System.out.println(baseUserTest.getId());
        System.out.println(baseUserTest.getFirstName());
        System.out.println(baseUserTest.getLastName());
        System.out.println(baseUserTest.getEmailAddress());
        System.out.println(baseUserTest.getProfilePic());
        System.out.println(baseUserTest.getIsAdmin());
        //System.out.println(baseUserTest.getPasswordForTestingOnly()); Test passed
    }

    @Test
    public void UserValidationTest() {
        // Arrange

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        User user = new User();
        user.setId(UUID.randomUUID());

        // Deliberately setting invalid values
        user.setFirstName("");         // violates @NotBlank, @Size
        user.setLastName(null);        // violates @NotNull, @NotBlank
        user.setEmailAddress("invalid-email"); // violates @Email
        user.setPassword("");          // violates @NotBlank
        user.setIsAdmin(null);         // violates @NotNull

        // Act
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Assert
        assertThat(violations).hasSizeGreaterThan(0);

        assertThat(violations)
            .anySatisfy(v -> {
                assertThat(v.getPropertyPath().toString()).isEqualTo("firstName");
                assertThat(v.getMessage()).contains("must not be blank");
            })
            .anySatisfy(v -> {
                assertThat(v.getPropertyPath().toString()).isEqualTo("lastName");
                assertThat(v.getMessage()).contains("must not be null");
            })
            .anySatisfy(v -> {
                assertThat(v.getPropertyPath().toString()).isEqualTo("emailAddress");
                assertThat(v.getMessage()).contains("must be a well-formed email address");
            })
            .anySatisfy(v -> {
                assertThat(v.getPropertyPath().toString()).isEqualTo("password");
                assertThat(v.getMessage()).contains("must not be blank");
            })
            .anySatisfy(v -> {
                assertThat(v.getPropertyPath().toString()).isEqualTo("isAdmin");
                assertThat(v.getMessage()).contains("must not be null");
            });

    }
}
