package com.example.secretscribe.model.exceptions;

public class UserNameAlreadyExistsException  extends RuntimeException{
    public UserNameAlreadyExistsException(String username) {
        super(String.format("User with username: %s already exists", username));
    }

}
