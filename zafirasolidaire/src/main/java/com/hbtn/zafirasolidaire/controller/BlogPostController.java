package com.hbtn.zafirasolidaire.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbtn.zafirasolidaire.dto.BlogPostDto;
import com.hbtn.zafirasolidaire.dto.RequestBlogPostDto;
import com.hbtn.zafirasolidaire.dto.RequestPhotoDto;
import com.hbtn.zafirasolidaire.model.BlogPost;
import com.hbtn.zafirasolidaire.service.BlogPostFacade;


import jakarta.validation.Valid;

@RestController
@RequestMapping("blog_posts")
public class BlogPostController {
    private final BlogPostFacade blogPostFacade;

    @Autowired
    public BlogPostController(BlogPostFacade blogPostFacade) {
        this.blogPostFacade = blogPostFacade;
    }

    // ---------- POST ----------//

    // Save a single blogPost
    @PostMapping
    public ResponseEntity<BlogPostDto> saveBlogPost(@RequestBody @Valid RequestBlogPostDto requestBlogPostDto) {
        BlogPostDto createdPost = blogPostFacade.createBlogPost(requestBlogPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBlogPost(@PathVariable String id, @RequestBody RequestBlogPostDto requestBlogPostDto) {
        UUID idFromString = UUID.fromString(id);
        if (!blogPostFacade.existsById(idFromString)) {
            return ResponseEntity.notFound().build();
        }
        blogPostFacade.updateBlogPost(idFromString, requestBlogPostDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // // Save multiple blogPosts
    // @PostMapping("/batch")
    // public ResponseEntity<String> saveAllBlogPosts(@RequestBody @Valid List<BlogPostDto> blogPostDtos) {
    //     blogPostFacade.createAllBlogPosts(blogPostDtos);
    //     return ResponseEntity.status(HttpStatus.CREATED).build();
    // }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{blogPostId}/photo")
    public ResponseEntity<Void> saveBlogPost(@RequestBody @Valid RequestPhotoDto photoDto, @PathVariable UUID blogPostId) {
        blogPostFacade.addPhoto(photoDto, blogPostId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Get blogPost by ID
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<BlogPostDto> getBlogPostById(@PathVariable UUID id) {
        BlogPostDto blogPostDto = blogPostFacade.getBlogPostById(id);
        return ResponseEntity.ok(blogPostDto);
    }

    // Check if blogPost exists by ID
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable UUID id) {
        boolean exists = blogPostFacade.existsById(id);
        return ResponseEntity.ok(exists);
    }

    // Get all blogPosts
    @GetMapping
    public ResponseEntity<Iterable<BlogPostDto>> getAllBlogPosts() {
        Iterable<BlogPostDto> blogPosts = blogPostFacade.getAllBlogPosts();
        return ResponseEntity.ok(blogPosts);
    }

    // Count blogPosts
    @GetMapping("/count")
    public ResponseEntity<Long> countBlogPosts() {
        long count = blogPostFacade.countBlogPosts();
        return ResponseEntity.ok(count);
    }

    // ---------- DELETE ----------//

    // Delete blogPost by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPostById(@PathVariable UUID id) {
        blogPostFacade.deleteBlogPostById(id);
        return ResponseEntity.noContent().build();
    }

    // Delete a single blogPost (full object)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> deleteBlogPost(@RequestBody BlogPost blogPost) {
        blogPostFacade.deleteBlogPost(blogPost);
        return ResponseEntity.noContent().build();
    }

    // // Delete all blogPosts
    // @DeleteMapping("/all")
    // public ResponseEntity<Void> deleteAllBlogPosts() {
    //     blogPostFacade.deleteAllBlogPosts();
    //     return ResponseEntity.noContent().build();
    // }

    // // Delete specific blogPosts
    // @DeleteMapping("/batch")
    // public ResponseEntity<Void> deleteAllBlogPostsFromlist(@RequestBody List<BlogPost> blogPosts) {
    //     blogPostFacade.deleteAllBlogPosts(blogPosts);
    //     return ResponseEntity.noContent().build();
    // }
}
