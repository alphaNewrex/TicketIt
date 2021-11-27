package com.cooldevs.ticketit.models;

public class User {
    public String fname,email,phoneno,admin,username;

    public User (String fname,String email,String phoneno,String admin,String username)
    {
        this.fname= fname;
        this.email=email;
        this.phoneno=phoneno;
        this.admin=admin;
        this.username=username;
    }
}
