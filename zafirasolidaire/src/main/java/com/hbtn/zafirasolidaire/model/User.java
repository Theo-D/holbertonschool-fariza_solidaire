package com.hbtn.zafirasolidaire.model;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name ="Users")
public class User extends BaseModel {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email
    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @SuppressWarnings("unused")
    @Column( nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "photo_id", unique = true)
    private Photo profilePic;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    // ---------- Id setter ---------- //
    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    // ---------- firstName getter and setter ---------- //
    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    // ---------- lastName getter and setter ---------- //
    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    // ---------- emailAddress getter and setter ---------- //

    public String getEmail() {
        return emailAddress;
    }

    public User setEmail(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    // ---------- password setter ---------- //
    // Test passed - Commenting method
    // protected String getPasswordForTestingOnly() {
    //     return password;
    // }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    // ---------- profilePic getter and setter ---------- //

    public Photo getProfilePic() {
        return profilePic;
    }

    public User setProfilePic(Photo profilePic) {
        this.profilePic = profilePic;
        return this;
    }

    // ---------- isAdmin getter and setter ---------- //

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public User setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", creation date='" + createDate + '\'' +
            ", update date='" + updateDate + '\'' +
            ", email='" + emailAddress + '\'' +
            ", first name='" + firstName + '\'' +
            ", last name='" + lastName + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
