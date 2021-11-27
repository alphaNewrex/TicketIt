package com.cooldevs.ticketit.models;

public class User {
    public String fname,email,phoneno,username;
    public boolean admin;

    public User (String fname,String email,String phoneno,Boolean admin,String username)
    {
        this.fname= fname;
        this.email=email;
        this.phoneno=phoneno;
        this.admin=admin;
        this.username=username;
    }
}
