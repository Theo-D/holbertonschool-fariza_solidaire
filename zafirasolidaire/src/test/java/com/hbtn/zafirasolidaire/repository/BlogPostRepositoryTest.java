package com.hbtn.zafirasolidaire.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.hbtn.zafirasolidaire.config.JpaConfigTest;
import com.hbtn.zafirasolidaire.model.BlogPost;
import com.hbtn.zafirasolidaire.model.Photo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import(JpaConfigTest.class)
public class BlogPostRepositoryTest {

    @Autowired
    BlogPostRepository blogPostRepository;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private BlogPost createValidBlogPost() {
        String textBody = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
               "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim" +
               "ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " +
               "ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate" +
               "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat" +
               "cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est" +
               "laborum.";

        return new BlogPost()
            .setTitle("Lorem Ipsum")
            .setTextBody(textBody.repeat(100))
            .setAuthor("Jean Lorem Ipsum")
            .setPhoto(new Photo().setUrl("https://example.com/photo.jpg"));
    }

    @Test
    void testSaveValidBlogPost() {
        // Arrange
        BlogPost blogPost = createValidBlogPost();

        // Act
        BlogPost saved = blogPostRepository.save(blogPost);

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(blogPost.getTitle(), saved.getTitle());
    }

    @Test
    void testValidationFailsWhenTitleIsNull() {
        BlogPost blogPost = createValidBlogPost().setTitle(null);

        Set<ConstraintViolation<BlogPost>> violations = validator.validate(blogPost);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
            v.getPropertyPath().toString().equals("title") &&
            v.getMessage().contains("must not be null")));
    }

    @Test
    void testValidationFailsWhenTitleTooLong() {
        BlogPost blogPost = createValidBlogPost().setTitle("A".repeat(101));

        Set<ConstraintViolation<BlogPost>> violations = validator.validate(blogPost);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
            v.getPropertyPath().toString().equals("title") &&
            v.getMessage().contains("Title must be between")));
    }

    @Test
    void testValidationFailsWhenTextBodyTooShort() {
        BlogPost blogPost = createValidBlogPost().setTextBody("Smol text");

        Set<ConstraintViolation<BlogPost>> violations = validator.validate(blogPost);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
            v.getPropertyPath().toString().equals("textBody") &&
            v.getMessage().contains("Text body must be at least")));
    }

    @Test
    void testValidationFailsWhenAuthorIsBlank() {
        BlogPost blogPost = createValidBlogPost().setAuthor(" ");

        Set<ConstraintViolation<BlogPost>> violations = validator.validate(blogPost);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v ->
            v.getPropertyPath().toString().equals("author") &&
            v.getMessage().contains("must not be blank")));
    }

    @Test
    void testSaveBlogPostWithPhoto() {
        BlogPost blogPost = createValidBlogPost();
        blogPost.setPhoto(new Photo().setUrl("https://example.com/blogphoto.jpg"));

        BlogPost saved = blogPostRepository.save(blogPost);

        assertNotNull(saved.getPhoto());
        assertEquals("https://example.com/blogphoto.jpg", saved.getPhoto().getUrl());
    }
}
