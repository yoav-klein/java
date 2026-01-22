package com.example.web.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.business.model.PersonForm;

@Controller
public class PersonController extends ResponseEntityExceptionHandler {

    @GetMapping("/") 
    public String showForm(Model model) {
        model.addAttribute("personForm", new PersonForm());

        return "index";
    }

    @GetMapping("/failing")
    public String fail() throws ServerErrorException {
        if(1==1) throw new ServerErrorException("something went wrong", null);

        return "index";
    }


    @Override
    public ResponseEntity<Object> handleErrorResponseException(
			ErrorResponseException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        if(!(ex instanceof ServerErrorException))  { 
            return super.handleErrorResponseException(ex, headers, status, request);
        }

        ProblemDetail body = ex.getBody();

        body.setProperty("errors", "Wowww");

        return super.handleExceptionInternal(ex, null, headers, status, request);
    }

}