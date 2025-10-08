package com.hbtn.zafirasolidaire.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Partners")
public class Partner extends BaseModel{

    @NotNull
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "homepage_url", nullable = true)
    private String homepageUrl;

    @Column(name = "logo_url", nullable = true)
    private String logoUrl;

    //---------- name getters and setters ----------//
    public String getName() {
        return name;
    }

    public Partner setName(String name) {
        this.name = name;
        return this;
    }

    //---------- homepageUrl getters and setters ----------//

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public Partner setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
        return this;
    }

    //---------- homepageUrl getters and setters ----------//

    public String getLogoUrl() {
        return logoUrl;
    }

    public Partner setLogoUrl(String logoUrl) {
        this.logoUrl= logoUrl;
        return this;
    }
}
