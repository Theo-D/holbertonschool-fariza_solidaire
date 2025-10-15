package com.hbtn.zafirasolidaire.dto;

import java.time.LocalDateTime;
import java.util.UUID;


public class PhotoDto {

    private UUID id;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private UUID userId;

    private UUID eventId;

    private UUID blogPostId;

    private String url;

    public UUID getId() {
    return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
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
