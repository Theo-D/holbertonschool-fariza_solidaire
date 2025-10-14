package com.hbtn.zafirasolidaire.dto;

public class UserLoginDto {

    private String emailAddress;

    private String password;


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
