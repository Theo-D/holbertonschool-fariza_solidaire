package com.hbtn.test_db.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private UUID id;

  private String name;

  private String email;

  @SuppressWarnings("unused")
private String password;

// ---------- id getter and setter ---------- //

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  // ---------- name getter and setter ---------- //

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // ---------- email getter and setter ---------- //

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  // ---------- password setter ---------- //

  public void setPassword(String password) {
    this.password = password;
  }
}
