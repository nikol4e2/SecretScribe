package com.example.secretscribe.model.exceptions;

public class ConfessionIsEmptyException extends RuntimeException{

    public ConfessionIsEmptyException() {
        super("The text of the confession was left empty");
    }
}
