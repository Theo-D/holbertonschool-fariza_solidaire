package com.hbtn.zafirasolidaire.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Events")
public class Event extends BaseModel {

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_category_id", nullable = false)
    private EventCategory category;

    @NotNull
    @Future(message = "Event date must be in the future")
    @Column(name = "event_date", nullable = false)
    private LocalDateTime date;

    @NotNull
    @Min(value = 1, message = "Events must host at least one person")
    @Column(name = "capacity", nullable = false)
    private int capacity;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "photo_id", unique = true)
    private Photo photo;

    //---------- category getters and setters  ----------//
    public Event setId(UUID id) {
        this.id = id;
        return this;
    }

    public EventCategory getCategory() {
        return category;
    }

    public Event setCategory(EventCategory category) {
        this.category = category;
        return this;
    }

    //---------- date getters and setters  ----------//

    public LocalDateTime getDate() {
        return date;
    }

    public Event setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    //---------- capacity getters and setters  ----------//

    public int getCapacity() {
        return capacity;
    }

    public Event setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    //---------- photo getters and setters  ----------//

    public Photo getPhoto() {
        return photo;
    }

    public Event setPhoto(Photo photo) {
        this.photo = photo;
        return this;
    }

}
