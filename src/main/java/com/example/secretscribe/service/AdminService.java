package com.example.secretscribe.service;

import com.example.secretscribe.model.Admin;

public interface AdminService {

    Admin register(String username, String password, String repeatPassword);
    Admin login(String username, String password);
}
