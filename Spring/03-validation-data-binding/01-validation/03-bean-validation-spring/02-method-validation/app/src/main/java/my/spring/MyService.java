package my.spring;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class MyService {
    
    public @NotNull Object myValidMethod(@NotNull String arg1, @Max(10) int arg2) {

        System.out.println(arg1 + arg2);

        return "kuku";
    }

    public void takeValidUser(@Valid User u) {
        System.out.println(u.getName());
    }

    public void takeValidUsers(@Valid List<User> users) {
        users.forEach(user -> { System.out.println(user.getName()); });
        
    }

    // calling from within the class won't trigger validation, because of how AOP works
    public void calling() {
        myValidMethod("Yehuda", 200);
    }
    
}
