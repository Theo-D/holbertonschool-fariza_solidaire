package com.hbtn.zafirasolidaire.dto;

import java.util.UUID;

public class UserDto {

    private UUID userId;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private String emailAddress;
    private boolean isAdmin;
    private boolean isServiced;

    //------- userId getters and setters -------//
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    //------- firstName getters and setters -------//
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //------- lastName getters and setters -------//
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //------- photoUrl getters and setters -------//
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    //------- photoUrl getters and setters -------//
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    //------- isAdmin getters and setters -------//
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    //------- isServiced getters and setters -------//
    public boolean getIsServiced() {
        return isServiced;
    }

    public void setIsServiced(boolean isServiced) {
        this.isServiced = isServiced;
    }

    //------ Helper Methods -----//

    public String toString() {
        return "UserDto{" +
            "userId=" + userId + '\'' +
            ", first name= '" + firstName + '\'' +
            ", last name= '" + lastName + '\'' +
            ", photoUrl= '" + photoUrl + '\'' +
            ", isAdmin= '" + isAdmin + '\'' +
            '}';
    }
}
