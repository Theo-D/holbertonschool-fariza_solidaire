package com.hbtn.zafirasolidaire.model;

import org.springframework.data.repository.query.parser.Part;

public class Partner extends BaseModel{
    private String name;
    private String homepageUrl;
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
