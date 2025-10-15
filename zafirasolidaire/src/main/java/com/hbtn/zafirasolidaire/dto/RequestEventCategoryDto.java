package com.hbtn.zafirasolidaire.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RequestEventCategoryDto {

    @NotBlank
    @Size(max = 100)
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
