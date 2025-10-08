package com.hbtn.zafirasolidaire.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class BlogPostTest {

    static class BaseBlogPostTest extends BlogPost {
        public BaseBlogPostTest setBlogId(UUID id) {
            super.id = id;
            return this;
        }
    }

    private Validator validator;

    @BeforeEach
    void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void blogPostModelCreationTest() {
        // Arrange
        BaseBlogPostTest blogPost = new BaseBlogPostTest();
        Photo photo = new Photo();
        String textBody = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
               "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim" +
               "ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " +
               "ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate" +
               "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat" +
               "cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est" +
               "laborum.";

        // Act
        blogPost.onCreate();

        blogPost.setBlogId(UUID.randomUUID())
               .setTitle("Lorem Ipsum")
               .setTextBody(textBody.repeat(100))
               .setAuthor("Jane Doe")
               .setPhoto(photo);

        blogPost.onUpdate();

        // Assert
        assertThat(blogPost.getId()).isNotNull().isInstanceOf(UUID.class);
        assertThat(blogPost.getCreateDate()).isNotNull().isInstanceOf(Date.class);
        assertThat(blogPost.getUpdateDate()).isNotNull().isAfter(blogPost.getCreateDate()).isInstanceOf(Date.class);
        assertThat(blogPost.getTitle()).isNotNull().isInstanceOf(String.class);
        assertThat(blogPost.getTextBody()).isNotNull().isInstanceOf(String.class);
        assertThat(blogPost.getAuthor()).isNotNull().isInstanceOf(String.class);
        assertThat(blogPost.getPhoto()).isNotNull().isInstanceOf(Photo.class);

        System.out.println("ID: " + blogPost.getId());
        System.out.println("Title: " + blogPost.getTitle());
        System.out.println("TextBody: " + blogPost.getTextBody());
        System.out.println("Author: " + blogPost.getAuthor());
        System.out.println("Photo: " + blogPost.getPhoto());
    }

    @Test
    public void blogPostValidationTest() {
        // Arrange
        BaseBlogPostTest blogPost = new BaseBlogPostTest();
        blogPost.setBlogId(UUID.randomUUID());

        // Deliberately setting invalid values
        blogPost.setTitle("");
        blogPost.setTextBody("Too short");
        blogPost.setAuthor(null);
        blogPost.setPhoto(null);
        // Act
        Set<ConstraintViolation<BlogPost>> violations = validator.validate(blogPost);

        // Assert
        assertThat(violations).hasSizeGreaterThan(0);

        assertThat(violations)
            .anySatisfy(v -> {
                assertThat(v.getPropertyPath().toString()).isEqualTo("title");
                assertThat(v.getMessage()).contains("must not be blank");
            })
            .anySatisfy(v -> {
                assertThat(v.getPropertyPath().toString()).isEqualTo("textBody");
                assertThat(v.getMessage()).contains("must be at least 50 characters");
            })
            .anySatisfy(v -> {
                assertThat(v.getPropertyPath().toString()).isEqualTo("author");
                assertThat(v.getMessage()).contains("must not be null");
            });
    }
}
