package spring.mvc;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@SessionAttributes("user")
public class RegistrationController {

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @RequestMapping(value = "/step1", method = RequestMethod.GET)
    public String showStep1(Model model) {
        // Ensures user object is present in model
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "step1";
    }

    @RequestMapping(value = "/step1", method = RequestMethod.POST)
    public String processStep1(@ModelAttribute("user") User user) {
        // Save user info from step 1
        return "redirect:/step2";
    }

    @RequestMapping(value = "/step2", method = RequestMethod.GET)
    public String showStep2() {
        return "step2";
    }

    @RequestMapping(value = "/step2", method = RequestMethod.POST)
    public String processStep2(@ModelAttribute("user") User user) {
        // Save user info from step 2
        return "redirect:/step3";
    }

    @RequestMapping(value = "/step3", method = RequestMethod.GET)
    public String showStep3() {
        return "step3";
    }

    @RequestMapping(value = "/step3", method = RequestMethod.POST)
    public String processStep3(@ModelAttribute("user") User user, Model model) {
        // Save user info from step 3 and complete registration
        model.addAttribute("user", user); // Ensure user is in model
        return "result";
    }
}
