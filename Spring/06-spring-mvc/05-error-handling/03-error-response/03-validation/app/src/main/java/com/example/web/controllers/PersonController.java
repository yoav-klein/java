package com.example.web.controllers;


import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ServerErrorException;

import com.example.business.model.PersonForm;

@Controller
public class PersonController {

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

    @ExceptionHandler
    public ProblemDetail handleServerError(ServerErrorException e) {
        ProblemDetail ret = e.getBody();

        ret.setProperty("error", "wtf?!");
        
        return ret;

    } 

}