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
    private BlogPost blogpost;

    public String getUrl() {
        return url;
    }

    public Photo setUrl(String url) {
        this.url = url;
        return this;
    }
}
