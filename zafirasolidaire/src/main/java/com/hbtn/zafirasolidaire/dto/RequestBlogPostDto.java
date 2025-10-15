package com.hbtn.zafirasolidaire.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequestBlogPostDto {

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String textBody;

    @NotNull
    @NotBlank
    private String author;

    private String photoUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "BlogPostDto{" +
            ", title='" + title + '\'' +
            ", textBody='" + textBody + '\'' +
            ", author='" + author + '\'' +
            ", photoUrl='" + photoUrl + '\'' +
            '}';
    }
}
