package com.hbtn.zafirasolidaire.dto;

import java.time.LocalDateTime;
// import java.util.UUID;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequestEventDto {

    @NotNull
    @NotBlank
    private String category;

    @NotNull
    @Future
    private LocalDateTime date;

    @NotNull
    @Min(value = 1, message = "Events must host at least one person")
    private int capacity;

    private String photoUrl;

    private String url;

    private String description;

    //---------- id getters and setters  ----------//
    // public UUID getEventId() {
    //     return this.eventId;
    // }

    // public void setEventId(UUID eventId) {
    //     this.eventId = eventId;
    // }

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
