package com.example.business.exception;

public class UserAlreadyInTenantException extends Exception {
    public UserAlreadyInTenantException(String message) {
        super(message);
    }

    public UserAlreadyInTenantException() {
        super();
    }
}
