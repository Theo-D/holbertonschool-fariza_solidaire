package com.hbtn.zafirasolidaire.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.hbtn.zafirasolidaire.model.BaseModel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class RequestServicedUserDto extends BaseModel{

    @Schema(hidden = true)
    private UUID id;

    @Schema(hidden = true)
    private LocalDateTime createDate;

    @Schema(hidden = true)
    private LocalDateTime updateDate;

    @NotNull
    private UUID userId;

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
