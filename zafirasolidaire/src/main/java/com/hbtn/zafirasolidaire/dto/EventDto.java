package com.hbtn.zafirasolidaire.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventDto {

    private UUID id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String category;
    private LocalDateTime date;
    private int capacity;
    private String photoUrl;
    private String url;
    private String description;

    //---------- id getters and setters  ----------//
    public UUID getId() {
        return this.id;
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

    //---------- category getters and setters  ----------//
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //---------- date getters and setters  ----------//
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    //---------- capacity getters and setters  ----------//
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    //---------- photoUrl getters and setters  ----------//
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
