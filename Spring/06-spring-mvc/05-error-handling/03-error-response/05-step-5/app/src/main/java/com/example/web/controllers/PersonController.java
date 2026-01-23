package com.example.web.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.business.model.PersonForm;

@Controller
public class PersonController extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    public void setMessageSource(MessageSource ms) {
        this.messageSource = ms;
    }

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

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemDetail body = ex.getBody();

        List<Map<String, String>> localizedErrors = ex.getFieldErrors()
            .stream()
            .map(error -> Map.of(
                "field", error.getField(),
                // Manual translation for the 'reason' using the key from the error
                "reason", this.messageSource.getMessage(error, request.getLocale())
            ))
            .toList();

        body.setProperty("errors", localizedErrors);

        return super.handleExceptionInternal(ex, null, headers, status, request);
    }

}