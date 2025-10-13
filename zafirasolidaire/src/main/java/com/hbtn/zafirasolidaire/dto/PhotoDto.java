package com.hbtn.zafirasolidaire.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PhotoDto {

    @NotNull
    private UUID id;

    private UUID userId;

    private UUID eventId;

    private UUID blogPostId;

    @NotBlank
    @NotNull
    private String url;

    public UUID getId() {
    return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
    return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(UUID blogPostId) {
        this.blogPostId = blogPostId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
