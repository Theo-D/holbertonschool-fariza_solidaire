package com.hbtn.zafirasolidaire.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="refreshTokens")
public class RefreshToken extends BaseModel{

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expirationDate;

    public User getUser() {
        return user;
    }

    public RefreshToken setUSer(User user) {
        this.user = user;
        return this;
    }

    public String getToken() {
        return token;
    }

    public RefreshToken setToken(String token) {
        this.token = token;
        return this;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public RefreshToken setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }
}
