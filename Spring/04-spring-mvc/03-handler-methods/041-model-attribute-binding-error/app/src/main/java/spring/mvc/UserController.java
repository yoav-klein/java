package spring.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated; 

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

        // this will populate errors in the BindingResult object (if any errors occur)
        @PostMapping("/submitDetails1")
        public String submitDetails(@ModelAttribute("person") Person person, BindingResult result, Model model) { 
        if (result.hasErrors()) {
                return "error";
        }
            // Process the data (e.g., save to database)
        
            return "person-result"; // Return the view name
        }

        // this will throw an exception if data binding error occurs
        @PostMapping("/submitDetails2")
        public String submitDetails(@ModelAttribute("person") Person person, Model model) { 

            return "person-result"; // Return the view name
        }

        @GetMapping("/person")
        public String addPerson() {
                return "person-form";
        }

}
