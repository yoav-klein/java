package com.example.business.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User is not registered")
public class UserNotFoundException extends Exception {
    
}
