package com.invex.api.employees.application.exception;

public class NotFountException extends RuntimeException{

    private int code;
    private String message;

    public NotFountException(String message) {
        super(message);
        this.message = message;
    }
}
