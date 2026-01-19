package com.example.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.bind.annotation.RestController;
import com.example.business.model.PersonForm;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Controller
public class PersonController {

    @GetMapping("/") 
    public String showForm(Model model) {
        model.addAttribute("personForm", new PersonForm());

        return "index";
    }

    // this demonstrates using BindingResult to handle the error "manually"
    @PostMapping("/person")
    public String createPerson(@Validated @ModelAttribute PersonForm person, BindingResult result) {
        if (result.hasErrors()) {
            // Return the validation errors
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> System.out.println(error.toString()));
            return "index";
        }

        // Handle the logic for creating the person
        // For example, save the person to a database (this part is omitted for simplicity)

        return "success";
    }

    // this demonstrates not using BindingResult
    // in this case, the methodArgumentValidation will play
    @PostMapping("/person1")
    public String createPerson(@Validated @ModelAttribute PersonForm person) {
       
        // Handle the logic for creating the person
        // For example, save the person to a database (this part is omitted for simplicity)

        return "success";
    }

    // this demonstrates validation of @RequestBody and how it works with JavaScript
    @PostMapping(path="/personRest")
    @ResponseBody
    public ResponseEntity restPerson(@Validated @RequestBody PersonForm person) {
        
        System.out.println("RECEIVED: " + person.getName() + " " + person.getAge());
        return new ResponseEntity(HttpStatus.OK);
    }

    
    /* // HandlerMethodValidationException is thrown when 
    @ExceptionHandler
    public String methodValidation(HandlerMethodValidationException e) {
        System.out.println("HandlerMethodValidationException");

        return "handlerMethodValidation";
    }

    @ExceptionHandler
    public String argumentValidation(MethodArgumentNotValidException e) {
        System.out.println("HandlerMethodValidationException");

        return "methodArgumentValidation";
    } */


}