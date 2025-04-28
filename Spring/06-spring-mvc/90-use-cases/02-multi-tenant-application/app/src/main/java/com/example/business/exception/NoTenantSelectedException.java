package com.example.business.exception;

public class NoTenantSelectedException extends RuntimeException {
    public NoTenantSelectedException(String message) {
        super(message);
    }

    public NoTenantSelectedException() {
        super();
    }
}
