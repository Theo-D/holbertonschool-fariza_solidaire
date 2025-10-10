package com.hbtn.zafirasolidaire.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clothing")
public class Clothing {

    @Column
    public static final UUID CLOTHING_UUID = UUID.fromString("7fdc74b2-c8c1-41ba-89d2-efa772d4b627");

    @Column(name = "weight", nullable = false)
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
