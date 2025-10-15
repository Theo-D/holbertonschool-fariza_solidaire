package com.hbtn.zafirasolidaire.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.hbtn.zafirasolidaire.dto.BlogPostDto;
import com.hbtn.zafirasolidaire.dto.RequestBlogPostDto;
import com.hbtn.zafirasolidaire.dto.RequestPhotoDto;
import com.hbtn.zafirasolidaire.mapper.BlogPostMapper;
import com.hbtn.zafirasolidaire.model.BlogPost;
import com.hbtn.zafirasolidaire.model.Photo;
import com.hbtn.zafirasolidaire.repository.BlogPostRepository;

@Service
public class BlogPostFacade {
    private final BlogPostMapper blogPostMapper;
    private final BlogPostRepository blogPostRepository;
    private final PhotoFacade photoFacade;

    public BlogPostFacade(BlogPostMapper blogPostMapper, BlogPostRepository blogPostRepository, PhotoFacade photoFacade) {
        this.blogPostMapper = blogPostMapper;
        this.blogPostRepository= blogPostRepository;
        this.photoFacade = photoFacade;
    }

    //---------- Repository Services ----------//
    public void createBlogPost(RequestBlogPostDto requestBlogPostDto) {
        if (requestBlogPostDto == null) {
            throw new IllegalArgumentException("BlogPost cannot be null.");
        }

        BlogPost blogPost = blogPostMapper.requestDtoToBlogPost(requestBlogPostDto);

        blogPostRepository.save(blogPost);
    }

    public void createAllBlogPosts(Iterable<RequestBlogPostDto> requestBlogPostDtos) {
        if (requestBlogPostDtos == null || !requestBlogPostDtos.iterator().hasNext()) {
            throw new IllegalArgumentException("BlogPost list cannot be null or empty.");
        }

        List<BlogPost> blogPosts = new ArrayList<>();

        for (RequestBlogPostDto requestBlogPostDto : requestBlogPostDtos) {
            BlogPost blogPost = blogPostMapper.requestDtoToBlogPost(requestBlogPostDto);
            blogPosts.add(blogPost);
        }
        blogPostRepository.saveAll(blogPosts);
    }

    public void addPhoto(RequestPhotoDto photoDto, UUID blogPostId) {
        if (photoDto == null) {
            throw new IllegalArgumentException("Photo DTO cannot be null.");
        } else if (blogPostId == null) {
            throw new IllegalArgumentException("BlogPost ID cannot be null.");
        }

        BlogPost blogPost = blogPostRepository.findById(blogPostId)
                                              .orElseThrow(() -> new IllegalArgumentException("BlogPost not found"));

        // Create and persist Photo
        Photo photo = photoFacade.mapDtoToPhoto(photoDto);
        photo.setBlogPost(blogPost);
        photoFacade.savePhoto(photo);

        // Link Photo to BlogPost
        blogPost.setPhoto(photo);
        blogPostRepository.save(blogPost);
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
