package com.hbtn.zafirasolidaire.mapper;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.hbtn.zafirasolidaire.dto.BlogPostDto;
import com.hbtn.zafirasolidaire.model.BlogPost;
import com.hbtn.zafirasolidaire.model.Photo;

public class BlogPostMapperTest {

    static class BaseBlogPostTest extends BlogPost {

        public BaseBlogPostTest setId(UUID id) {
            super.id = id;
            return this;
        }
    }

    private final BlogPostMapper mapper = Mappers.getMapper(BlogPostMapper.class);

    @Test
    public void testBlogPostToDto() {
        // Arrange
        UUID id = UUID.randomUUID();

        Photo photo = new Photo();
        photo.setUrl("https://example.com/blog-image.jpg");

        BlogPost blogPost = new BaseBlogPostTest()
                .setId(id)
                .setTitle("Understanding Spring Events")
                .setTextBody("This blog post explains how to use Spring application events...")
                .setAuthor("John Doe")
                .setPhoto(photo);

        // Act
        BlogPostDto dto = mapper.BlogPostToDto(blogPost);

        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getBlogPostId()).isEqualTo(id);
        assertThat(dto.getTitle()).isEqualTo("Understanding Spring Events");
        assertThat(dto.getTextBody()).isEqualTo("This blog post explains how to use Spring application events...");
        assertThat(dto.getAuthor()).isEqualTo("John Doe");
        assertThat(dto.getPhotoUrl()).isEqualTo("https://example.com/blog-image.jpg");

        System.out.println("Mapped DTO title: " + dto.getTitle());
    }

    @Test
    public void testDtoToBlogPost() {
        // Arrange
        UUID id = UUID.randomUUID();
        BlogPostDto dto = new BlogPostDto();
        dto.setBlogPostId(id); // should be ignored in mapping
        dto.setTitle("Event-Driven Microservices");
        dto.setTextBody("Exploring the benefits and trade-offs of event-driven architecture...");
        dto.setAuthor("Jane Smith");
        dto.setPhotoUrl("https://example.com/microservices.jpg");

        // Act
        BlogPost blogPost = mapper.dtoToBlogPost(dto);

        // Assert
        assertThat(blogPost).isNotNull();
        assertThat(blogPost.getId()).isNull(); // because id is ignored during mapping
        assertThat(blogPost.getTitle()).isEqualTo("Event-Driven Microservices");
        assertThat(blogPost.getTextBody()).isEqualTo("Exploring the benefits and trade-offs of event-driven architecture...");
        assertThat(blogPost.getAuthor()).isEqualTo("Jane Smith");
        assertThat(blogPost.getPhoto()).isNotNull();
        assertThat(blogPost.getPhoto().getUrl()).isEqualTo("https://example.com/microservices.jpg");

        System.out.println("BlogPostDto instance: " + dto);
        System.out.println("BlogPost instance: " + blogPost);
    }
}
