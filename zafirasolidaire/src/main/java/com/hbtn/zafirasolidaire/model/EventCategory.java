package com.hbtn.zafirasolidaire.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name ="EventCategories")
public class EventCategory extends BaseModel{

    @NotNull
    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Event> events = new HashSet<>();

    //---------- name getters and setters ----------//

    public String getName() {
        return this.name;
    }

    public EventCategory setName(String name) {
        this.name = name;
        return this;
    }
}
