package spring.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated; 
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

        @InitBinder
        void initBinder(WebDataBinder binder) {
                binder.registerCustomEditor(Person.class, new CustomPersonEditor());
        }

        @GetMapping("/person")
        public String addPerson(@RequestParam("person") Person person, Model model) {
                model.addAttribute(person);
                
                return "person-result";
        }

}
