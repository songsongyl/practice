package org.example.practice.exception;


public class UserRegisterException extends RuntimeException {
    public UserRegisterException(String message) {
        super(message);
    }
}
