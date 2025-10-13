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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name ="Users")
public class User extends BaseModel {

    @NotBlank
    @NotNull
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email
    @NotBlank
    @NotNull
    @Size(max = 100)
    @Column(name = "email_address", nullable = false, unique = true)
    private String emailAddress;

    @SuppressWarnings("unused")
    @NotBlank
    @NotNull
    @Size(min = 8)
    @Column( nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "photo_id", unique = true)
    private Photo profilePic;

    @NotNull
    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    @NotNull
    @Column(name = "is_serviced", nullable = false)
    private Boolean isServiced = false;

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public User setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    //---------- password setter ---------- //
    //Test passed - Commenting method
    public String getPasswordForTestingOnly() {
        return password;
    }

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

    // ---------- isServiced getter and setter ---------- //

    public Boolean getIsServiced() {
        return isServiced;
    }

    public User setIsServiced(Boolean isServiced) {
        this.isServiced = isServiced;
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
