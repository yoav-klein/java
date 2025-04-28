package com.example.business.exception;

public class UserAlreadyInTenantException extends RuntimeException {
    public UserAlreadyInTenantException(String message) {
        super(message);
    }

    public UserAlreadyInTenantException() {
        super();
    }
}
