package com.example.secretscribe.model;

import lombok.Getter;

@Getter
public class Admin {

    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
