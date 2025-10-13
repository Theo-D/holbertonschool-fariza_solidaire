package com.hbtn.zafirasolidaire.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class ServicedUserDto {

    @NotNull
    private UUID id;

    @NotNull
    private UUID userId;

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

    @Override
    public String toString() {
        return "ServicedUser{" +
            "id=" + id +
            ", userId='" + userId + '\'' +
            '}';
    }
}
