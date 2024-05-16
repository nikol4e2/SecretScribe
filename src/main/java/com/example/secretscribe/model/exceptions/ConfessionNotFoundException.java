package com.example.secretscribe.model.exceptions;

public class ConfessionNotFoundException extends RuntimeException{

    public ConfessionNotFoundException(Long id) {
        super(String.format("Confession with id %d not found",id));
    }
}
