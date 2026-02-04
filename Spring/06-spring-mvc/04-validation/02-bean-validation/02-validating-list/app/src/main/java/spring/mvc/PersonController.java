package spring.mvc;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import jakarta.validation.Valid;

@Controller
@Validated
public class PersonController {

    @GetMapping("/") 
    public String showForm() {
        return "index";
    }

    @PostMapping("/people")
    public ResponseEntity createPeople(@Valid @RequestBody ArrayList<Person> people) {

        System.out.println("PEOPLE");

        people.forEach(person -> System.out.println(person.getName() + " " + person.getAge()));
        
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/person")
    public ResponseEntity createPerson(@Validated @RequestBody Person person) {

        System.out.println("PERSON");

        System.out.println(person.getName() + " " + person.getAge());        
        
        return new ResponseEntity(HttpStatus.OK);
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