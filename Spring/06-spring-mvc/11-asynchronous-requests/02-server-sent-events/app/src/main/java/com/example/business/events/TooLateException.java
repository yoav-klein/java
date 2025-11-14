package com.example.business.events;

public class TooLateException extends Exception {
    public TooLateException(String message) {
        super(message);
    }
}
