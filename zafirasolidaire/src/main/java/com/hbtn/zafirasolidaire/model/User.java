package com.hbtn.zafirasolidaire.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name ="Users")
public class User extends BaseModel {

    private String firstName;
    private String lastName;
    @Email
    @NotBlank
    @NotEmpty
    private String emailAddress;
    @SuppressWarnings("unused")
    private String password;
    private Photo profilePic;
    private Boolean isAdmin;

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

    //TODO: Implement Email verification Service
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

    //TODO: Implement password hash
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

    public Boolean isAdmin() {
        return isAdmin;
    }

    protected User setAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }
}
