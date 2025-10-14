package com.hbtn.zafirasolidaire.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequest {

    @NotBlank
    @NotNull
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String firstName;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String lastName;

    @Email
    @NotBlank
    @NotNull
    @Size(max = 100)
    private String emailAddress;

    @NotBlank
    @NotNull
    @Size(min = 8, message = "")
    private String password;

    //----------- First Name Getters and Setters -----------//
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //----------- Last Name Getters and Setters -----------//
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //----------- Email Getters and Setters -----------//
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    //----------- First Name Getters and Setters -----------//
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
