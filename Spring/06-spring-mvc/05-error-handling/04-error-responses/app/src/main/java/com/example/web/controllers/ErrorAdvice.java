package com.example.web.controllers;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ErrorAdvice extends ResponseEntityExceptionHandler {
    
}
