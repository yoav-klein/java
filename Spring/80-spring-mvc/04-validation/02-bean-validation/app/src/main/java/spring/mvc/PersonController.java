package spring.mvc;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.annotation.Validated;

import org.springframework.validation.Validator;

import org.springframework.ui.Model;

@Controller
public class PersonController {

    @GetMapping("/person") 
    public String showForm(Model model) {
        model.addAttribute("personForm", new Person());

        return "personForm";
    }

    @PostMapping("/person")
    public String createPerson(@Validated @ModelAttribute Person person, BindingResult result) {
        if (result.hasErrors()) {
            // Return the validation errors
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> System.out.println(error.toString()));
            return "personForm";
        }

        // Handle the logic for creating the person
        // For example, save the person to a database (this part is omitted for simplicity)

        return "success";
    }
}