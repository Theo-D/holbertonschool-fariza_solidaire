package com.hbtn.zafirasolidaire.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbtn.zafirasolidaire.dto.BlogPostDto;
import com.hbtn.zafirasolidaire.mapper.BlogPostMapper;
import com.hbtn.zafirasolidaire.model.BlogPost;
import com.hbtn.zafirasolidaire.repository.BlogPostRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BlogPostFacadeTest {

    @Mock
    private BlogPostMapper blogPostMapper;

    @Mock
    private BlogPostRepository blogPostRepository;

    @InjectMocks
    private BlogPostFacade blogPostFacade;

    private BlogPostDto blogPostDto;
    private BlogPost blogPost;
    private UUID blogPostId;

    @BeforeEach
    void setUp() {
        blogPostId = UUID.randomUUID();
        blogPostDto = new BlogPostDto();
        blogPost = new BlogPost();
    }

    // createBlogPost
    @Test
    void createBlogPost_shouldSaveBlogPost() {
        when(blogPostMapper.dtoToBlogPost(blogPostDto)).thenReturn(blogPost);
        blogPostFacade.createBlogPost(blogPostDto);
        verify(blogPostRepository).save(blogPost);
    }

    @Test
    void createBlogPost_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> blogPostFacade.createBlogPost(null));
    }

    // createAllBlogPosts
    @Test
    void createAllBlogPosts_shouldSaveAllBlogPosts() {
        List<BlogPostDto> dtos = List.of(blogPostDto);
        when(blogPostMapper.dtoToBlogPost(blogPostDto)).thenReturn(blogPost);
        blogPostFacade.createAllBlogPosts(dtos);
        verify(blogPostRepository).saveAll(List.of(blogPost));
    }

    @Test
    void createAllBlogPosts_shouldThrowException_whenNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> blogPostFacade.createAllBlogPosts(null));
        assertThrows(IllegalArgumentException.class, () -> blogPostFacade.createAllBlogPosts(List.of()));
    }

    // getBlogPostById
    @Test
    void getBlogPostById_shouldReturnBlogPostDto() {
        when(blogPostRepository.findById(blogPostId)).thenReturn(Optional.of(blogPost));
        when(blogPostMapper.blogPostToDto(blogPost)).thenReturn(blogPostDto);

        BlogPostDto result = blogPostFacade.getBlogPostById(blogPostId);

        assertEquals(blogPostDto, result);
    }

    @Test
    void getBlogPostById_shouldThrowException_whenNotFound() {
        when(blogPostRepository.findById(blogPostId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> blogPostFacade.getBlogPostById(blogPostId));
    }

    @Test
    void getBlogPostById_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> blogPostFacade.getBlogPostById(null));
    }

    // existsById
    @Test
    void existsById_shouldReturnTrueIfExists() {
        when(blogPostRepository.existsById(blogPostId)).thenReturn(true);
        assertTrue(blogPostFacade.existsById(blogPostId));
    }

    @Test
    void existsById_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> blogPostFacade.existsById(null));
    }

    // getAllBlogPosts
    @Test
    void getAllBlogPosts_shouldReturnDtoList() {
        List<BlogPost> blogPosts = List.of(blogPost);
        when(blogPostRepository.findAll()).thenReturn(blogPosts);
        when(blogPostMapper.blogPostToDto(blogPost)).thenReturn(blogPostDto);

        Iterable<BlogPostDto> result = blogPostFacade.getAllBlogPosts();

        assertEquals(List.of(blogPostDto), result);
    }

    // getAllBlogPostsById
    @Test
    void getAllBlogPostsById_shouldReturnDtoList() {
        List<UUID> ids = List.of(blogPostId);
        List<BlogPost> blogPosts = List.of(blogPost);

        when(blogPostRepository.findAllById(ids)).thenReturn(blogPosts);
        when(blogPostMapper.blogPostToDto(blogPost)).thenReturn(blogPostDto);

        Iterable<BlogPostDto> result = blogPostFacade.getAllBlogPostsById(ids);

        assertEquals(List.of(blogPostDto), result);
    }

    @Test
    void getAllBlogPostsById_shouldThrowException_whenNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> blogPostFacade.getAllBlogPostsById(null));
        assertThrows(IllegalArgumentException.class, () -> blogPostFacade.getAllBlogPostsById(List.of()));
    }

    // deleteBlogPostById
    @Test
    void deleteBlogPostById_shouldCallRepository() {
        blogPostFacade.deleteBlogPostById(blogPostId);
        verify(blogPostRepository).deleteById(blogPostId);
    }

    @Test
    void deleteBlogPostById_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> blogPostFacade.deleteBlogPostById(null));
    }

    // deleteBlogPost
    @Test
    void deleteBlogPost_shouldCallRepository() {
        blogPostFacade.deleteBlogPost(blogPost);
        verify(blogPostRepository).delete(blogPost);
    }

    @Test
    void deleteBlogPost_shouldThrowException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> blogPostFacade.deleteBlogPost(null));
    }

    // deleteAllBlogPosts
    @Test
    void deleteAllBlogPosts_shouldCallRepository() {
        blogPostFacade.deleteAllBlogPosts();
        verify(blogPostRepository).deleteAll();
    }

    @Test
    void deleteAllBlogPosts_withIterable_shouldDelete() {
        List<BlogPost> blogPosts = List.of(blogPost);
        blogPostFacade.deleteAllBlogPosts(blogPosts);
        verify(blogPostRepository).deleteAll(blogPosts);
    }

    @Test
    void deleteAllBlogPosts_withIterable_shouldThrow_whenNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> blogPostFacade.deleteAllBlogPosts(null));
        assertThrows(IllegalArgumentException.class, () -> blogPostFacade.deleteAllBlogPosts(List.of()));
    }

    // countBlogPosts
    @Test
    void countBlogPosts_shouldReturnCount() {
        when(blogPostRepository.count()).thenReturn(42L);
        assertEquals(42L, blogPostFacade.countBlogPosts());
    }
}
