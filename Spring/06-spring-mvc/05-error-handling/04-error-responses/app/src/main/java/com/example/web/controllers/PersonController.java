package com.example.web.controllers;


import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.http.ProblemDetail;

import com.example.business.model.PersonForm;

@Controller
public class PersonController {

    @GetMapping("/") 
    public String showForm(Model model) {
        model.addAttribute("personForm", new PersonForm());

        return "index";
    }


    // this demonstrates validation of @RequestBody and how it works with JavaScript
    @PostMapping(path="/personRest")
    @ResponseBody
    public ResponseEntity restPerson(@Validated @RequestBody PersonForm person) {
        
        System.out.println("RECEIVED: " + person.getName() + " " + person.getAge());
        return new ResponseEntity(HttpStatus.OK);
    }
/* 
    @ExceptionHandler
    public ProblemDetail argumentValidation(MethodArgumentNotValidException e) {
        System.out.println("HandlerMethodValidationException");
        ProblemDetail ret = ProblemDetail.forStatus(400);

        for(Object o : e.getDetailMessageArguments()) {
            System.out.println(o);
        }
        
        ret.setProperty("errors", "wtf?!");
        return ret;

    } */



}