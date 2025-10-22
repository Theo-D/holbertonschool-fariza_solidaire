package com.hbtn.zafirasolidaire.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

//Placeholder class waiting to be implemented
@Entity
@Table(name = "photos")
public class Photo extends BaseModel {
    @NotBlank
    private String url;

    @OneToOne(mappedBy = "profilePic", optional = true)
    private User user;

    @OneToOne(mappedBy = "photo", optional = true)
    private Event event;

    @OneToOne(mappedBy = "photo", optional = true)
    private BlogPost blogPost;

    public String getUrl() {
        return url;
    }

    public Photo setUrl(String url) {
        this.url = url;
        return this;
    }

    public User getUser() {
    return user;
    }

    public Photo setUser(User user) {
        this.user = user;
        return this;
    }

    public Event getEvent() {
        return event;
    }

    public Photo setEvent(Event event) {
        this.event = event;
        return this;
    }

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public Photo setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
        return this;
    }
}
