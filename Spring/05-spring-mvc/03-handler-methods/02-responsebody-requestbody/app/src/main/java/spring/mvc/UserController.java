package spring.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        // Simulate saving the user to the database
        String responseMessage = "User created: " + user.getName() + ", Age: " + user.getAge();
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @GetMapping("/john")
    @ResponseBody
    public User getUser() {
        // Create a sample user
        User john = new User();
        john.setName("John Doe");
        john.setAge(30);
        
        return john;

    }
}
