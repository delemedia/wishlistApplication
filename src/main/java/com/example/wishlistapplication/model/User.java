package com.example.wishlistapplication.model;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;

    public User(){}
    public User(int userId, String userName, String userEmail, String userPassword) {
        this.id = userId;
        this.name = userName;
        this.email = userEmail;
        this.password = userPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void encodePassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
    }
}
