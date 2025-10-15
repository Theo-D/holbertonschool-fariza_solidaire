package com.hbtn.zafirasolidaire.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.hbtn.zafirasolidaire.model.BaseModel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class RequestServicedUserDto extends BaseModel{


    @SuppressWarnings("unused")
    private UUID id;

    @SuppressWarnings("unused")
    private LocalDateTime createDate;


    @SuppressWarnings("unused")
    private LocalDateTime updateDate;

    @NotNull
    private UUID userId;

    @Override
    @Schema(hidden = true)
    public UUID getId() {
        return super.getId();
    }

    @Override
    @Schema(hidden = true)
    public LocalDateTime getCreateDate() {
        return super.getCreateDate();
    }

    @Override
    @Schema(hidden = true)
    public LocalDateTime getUpdateDate() {
        return super.getUpdateDate();
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
            ", userId='" + userId + '\'' +
            '}';
    }
}
