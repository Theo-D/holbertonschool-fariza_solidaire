package com.hbtn.zafirasolidaire.model;

import java.util.Date;
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
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="UPDATE_DATE")
    private Date updateDate;

    // ---------- id getter and setter ---------- //
    public UUID getId() {
        return id;
    }

    // JPA handle in db id set with @GeneratedValue(strategy = GenerationType.UUID)

    // ---------- createdAt - updatedAt getter and setter ---------- //

    //Protected methods to be access by inheriting classes but not outside of class logic.
    @PrePersist
    protected void onCreate(){
        createDate = new Date();
        updateDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = new Date();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
