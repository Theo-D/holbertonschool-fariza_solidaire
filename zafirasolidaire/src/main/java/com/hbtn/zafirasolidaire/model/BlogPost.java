package com.hbtn.zafirasolidaire.model;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "BlogPosts")
public class BlogPost extends BaseModel {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 20, message = "Text body must be at least 50 characters")
    @Column(name = "text_body", columnDefinition = "TEXT", nullable = false)
    private String textBody;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50, message = "Author's name must be between 1 and 50 characters")
    @Column(name = "author", nullable = false)
    private String author;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "photo_id", unique = true)
    private Photo photo;

    //---------- Title getters and setters ----------//
    public BlogPost setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
    return title;
    }

    public BlogPost setTitle(String title) {
        this.title = title;
        return this;
    }

    //---------- text body getters and setters ----------//
    public String getTextBody() {
        return textBody;
    }

    public BlogPost setTextBody(String textBody) {
        this.textBody = textBody;
        return this;
    }

    //---------- author getters and setters ----------//
    public String getAuthor() {
        return author;
    }

    public BlogPost setAuthor(String author) {
        this.author = author;
        return this;
    }

    //---------- photo getters and setters ----------//
    public Photo getPhoto() {
        return photo;
    }

    public BlogPost setPhoto(Photo photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
            "id='" + id + '\'' +
            "title='" + title + '\'' +
            ", textBody='" + textBody + '\'' +
            ", author='" + author + '\'' +
            ", photo=" + (photo != null ? photo.toString() : "null") +
            '}';
    }
}
