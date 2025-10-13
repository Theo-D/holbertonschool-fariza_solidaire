package com.hbtn.zafirasolidaire.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//Placeholder class waiting to be implemented
@Entity
@Table(name = "Photos")
public class Photo extends BaseModel {
    private String url;

    @OneToOne(mappedBy = "profilePic", optional = false)
    private User user;

    @OneToOne(mappedBy = "photo", optional = false)
    private Event event;

    @OneToOne(mappedBy = "photo", optional = false)
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
