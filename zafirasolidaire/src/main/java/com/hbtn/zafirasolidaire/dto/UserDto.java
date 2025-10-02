package com.hbtn.zafirasolidaire.dto;

import java.util.UUID;

public class UserDto {

    private UUID userId;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private boolean isAdmin;

    //------- userId getters and setters -------//
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    //------- firstName getters and setters -------//
    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    //------- firstName getters and setters -------//
    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    //------- firstName getters and setters -------//
    public String getphotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    //------- firstName getters and setters -------//
    public boolean getisAdmin() {
        return isAdmin;
    }

    public void setisAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
