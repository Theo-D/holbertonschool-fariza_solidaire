package com.hbtn.zafirasolidaire.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    protected LocalDateTime createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="UPDATE_DATE")
    protected LocalDateTime updateDate;

    // ---------- id getter and setter ---------- //
    public UUID getId() {
        return id;
    }

    // JPA handle in db id set with @GeneratedValue(strategy = GenerationType.UUID)

    // ---------- createdAt - updatedAt getter and setter ---------- //

    //Protected methods to be access by inheriting classes but not outside of class logic.
    @PrePersist
    protected void onCreate(){
        createDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDateTime.now();
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
}
