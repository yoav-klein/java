package com.example.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.security.model.SecurityUser;
import com.example.business.service.FooService;

@Controller
public class HomeController {

    @Autowired
    private FooService fooService;

    @RequestMapping("/")
    public String sayHello(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
        model.addAttribute("user", securityUser.getAppUser());

        // test method security
        fooService.getUserPrivateData(securityUser.getAppUser().getId());
        // fooService.getUserPrivateData("POOO");

        return "index"; // This corresponds to the view name
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
   
}