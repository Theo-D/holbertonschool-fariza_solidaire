package com.hbtn.zafirasolidaire.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ServicedUsers")
public class ServicedUser extends BaseModel{

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ServicedUser setId(UUID id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public ServicedUser setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "ServicedUser{" +
            "id=" + id +
            ", userId='" + user.getId() + '\'' +
            '}';
    }
}
