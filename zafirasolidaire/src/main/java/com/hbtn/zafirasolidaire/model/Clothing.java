package com.hbtn.zafirasolidaire.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clothing")
public class Clothing {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(name = "weight", nullable = false)
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
