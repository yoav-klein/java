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

        @RequestMapping("/home")
        public String showHomePage(@ModelAttribute("nameInfo") NameInfoDTO nameInfoDTO) {
                
                return "welcome-page";
                
        }
        @RequestMapping("/process-homepage")
        public String showResultPage(@ModelAttribute("nameInfo") NameInfoDTO nameInfoDTO) {
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

}