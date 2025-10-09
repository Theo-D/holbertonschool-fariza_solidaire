package com.hbtn.zafirasolidaire.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.hbtn.zafirasolidaire.dto.BlogPostDto;
import com.hbtn.zafirasolidaire.mapper.BlogPostMapper;
import com.hbtn.zafirasolidaire.model.BlogPost;
import com.hbtn.zafirasolidaire.repository.BlogPostRepository;

@Service
public class BlogPostFacade {
    private final BlogPostMapper blogPostMapper;
    private final BlogPostRepository blogPostRepository;

    public BlogPostFacade(BlogPostMapper blogPostMapper, BlogPostRepository blogPostRepository) {
        this.blogPostMapper = blogPostMapper;
        this.blogPostRepository= blogPostRepository;
    }

    //---------- Repository Services ----------//
    public void createBlogPost(BlogPostDto blogPostDto) {
        if (blogPostDto == null) {
            throw new IllegalArgumentException("BlogPost cannot be null.");
        }

        BlogPost blogPost = blogPostMapper.dtoToBlogPost(blogPostDto);

        blogPostRepository.save(blogPost);
    }

    public void createAllBlogPosts(Iterable<BlogPostDto> blogPostDtos) {
        if (blogPostDtos == null || !blogPostDtos.iterator().hasNext()) {
            throw new IllegalArgumentException("BlogPost list cannot be null or empty.");
        }

        List<BlogPost> blogPosts = new ArrayList<>();

        for (BlogPostDto blogPostDto : blogPostDtos) {
            BlogPost blogPost = blogPostMapper.dtoToBlogPost(blogPostDto);
            blogPosts.add(blogPost);
        }
        blogPostRepository.saveAll(blogPosts);
    }

    public BlogPostDto getBlogPostById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("BlogPost ID cannot be null.");
        }

        BlogPost foundBlogPost = blogPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return blogPostMapper.blogPostToDto(foundBlogPost);
    }

    public boolean existsById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("BlogPost ID cannot be null.");
        }
        return blogPostRepository.existsById(id);
    }

    public Iterable<BlogPostDto> getAllBlogPosts() {
        return mapToDto(blogPostRepository.findAll());
    }

    public Iterable<BlogPostDto> getAllBlogPostsById(Iterable<UUID> ids) {
        if (ids == null || !ids.iterator().hasNext()) {
            throw new IllegalArgumentException("ID list cannot be null or empty.");
        }

        return mapToDto(blogPostRepository.findAllById(ids));
    }

    public void deleteBlogPostById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("BlogPost ID cannot be null.");
        }
        blogPostRepository.deleteById(id);
    }

    public void deleteBlogPost(BlogPost blogPost) {
        if (blogPost == null) {
            throw new IllegalArgumentException("BlogPost cannot be null.");
        }
        blogPostRepository.delete(blogPost);
    }

    public void deleteAllBlogPosts() {
        blogPostRepository.deleteAll();
    }

    public void deleteAllBlogPosts(Iterable<BlogPost> blogPosts) {
        if (blogPosts == null || !blogPosts.iterator().hasNext()) {
            throw new IllegalArgumentException("BlogPost list cannot be null or empty.");
        }
        blogPostRepository.deleteAll(blogPosts);
    }

    public long countBlogPosts() {
        return blogPostRepository.count();
    }

    //--------- Helper methods ---------//

    private Iterable<BlogPostDto> mapToDto(Iterable<BlogPost> blogPosts) {
        return StreamSupport.stream(blogPosts.spliterator(), false)
            .map(blogPostMapper::blogPostToDto)
            .collect(Collectors.toList());
    }
}
