package spring.mvc;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Controller
public class PersonController {

    @GetMapping("/") 
    public String showForm(Model model) {
        model.addAttribute("personForm", new Person());

        return "personForm";
    }


    // this demonstrates using BindingResult to handle the error "manually"
    @PostMapping("/person")
    public String createPerson(@Validated @ModelAttribute("personForm") Person person, BindingResult result) {
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

    // this demonstrates not using BindingResult
    @PostMapping("/person1")
    public String createPerson(@Valid @ModelAttribute Person person) {
       
        // Handle the logic for creating the person
        // For example, save the person to a database (this part is omitted for simplicity)

        return "success";
    }


    /**
     * 
     * Here I tried to understand the Spring MVC docs in Validation page.
     * It seemed from there that when method validation is in play (there is a @Constraint annotation on a parameter directly)
     * then it supersedes argument-level validation (@Validated), and then HandlerMethodValidationException is thrown
     * 
     * The template that sends requests to this endpoint is sending id as a request parameter (hardcoded in the template) 
     * and the Person form. when the id is not valid, it throws a HandlerMethodValidationException
     * but when the Person is not valid, it throws a MethodArgumentNotValidException
     * 
     * So I don't quite understand the docs
     * 
     * EDIT: It depends if Person is annotated with @Valid or @Validated. @Valid is causing a HandlerMethodValidationException, 
     * while @Validated causes a MethodArgumentNotValidException
     * 
     * 
     */
    @PostMapping("/person2")
    public String createPerson(@RequestParam("id") @Min(20) @Max(40) int id, @Valid @ModelAttribute Person person) {
        System.out.println("GOT A PERSON");
        System.out.println(id);
        return "success";
    }


    // HandlerMethodValidationException is thrown when 
    @ExceptionHandler
    public String methodValidation(HandlerMethodValidationException e) {
        System.out.println("HandlerMethodValidationException");

        return "handlerMethodValidationException";
    }

    @ExceptionHandler
    public String argumentValidation(MethodArgumentNotValidException e) {

        return "methodArgumentNotValidException";
    }
}