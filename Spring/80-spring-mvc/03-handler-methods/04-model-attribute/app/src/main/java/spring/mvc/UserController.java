package spring.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.NameInfoDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

        /*
         * @ModelAttribute on a method means that the method will be called before any RequestMapping method
         * and will populate the model with attributes.
         * 
         * In this case, it's a single attribute, which is of the return type String
         */
        @ModelAttribute("greet")
        public String greet() {
                return "Hello world";
        }

        /*
         * In this case, it's a ModelAttribute method that inserts multiple attributes
         */
        @ModelAttribute("group") 
        public void group(Model model) {
                model.addAttribute("groupName", "HaZofim");
                model.addAttribute("groupNumber", 13);
        }

        @RequestMapping("/home")
        public String showHomePage(@ModelAttribute("nameInfo") NameInfoDTO nameInfoDTO) {
                return "welcome-page";
                
        }

        @RequestMapping("/process-homepage")
        public String showResultPage(@ModelAttribute("nameInfo") NameInfoDTO nameInfo, Model model) {
                // model.addAttribute("nameInfo", nameInfo); // we don't need this
                return "result-page";
        }

        /* EQUIVALENT TO */
        /* 
        @RequestMapping("/home")
        public String showHomePage(Model model) {
                NameInfoDTO nameInfoDTO = new NameInfoDTO();
                model.addAttribute("nameInfo", nameInfoDTO);
                
                return "welcome-page";
        }
        @RequestMapping("/process-homepage")
        public String showResultPage(NameInfoDTO nameInfo, Model model) {
                model.addAttribute("nameInfo", nameInfo);
                return "result-page";
        }
        */

        // ANOTHER EXAMPLE

        // this demonstrates that you can take more than one @ModelAttribute argument in a handler method

        @PostMapping("/submitDetails")
        public String submitDetails(@ModelAttribute("person") Person person,
                                    @ModelAttribute("address") Address address,
                                    Model model) {
            // Process the data (e.g., save to database)
    
            
            return "person-result"; // Return the view name
        }

        @GetMapping("/person")
        public String addPerson() {
                return "person-form";
        }

}
