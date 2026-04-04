package org.example.web.controllers;

import org.example.business.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    FooService fooService;

    @RequestMapping("/")
    public String sayHello(Model model) {
        fooService.someImportantMethod();
        return "index"; // This corresponds to the view name
    }
   
}