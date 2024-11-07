package spring.mvc.test;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    public String[] getAllUsers() {
        return new String[] { "Amit Nadev", "Amit Nakesh" };
    }
}