package spring.mvc.business;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    public String[] getAllUsers() {
        return new String[] { "Noam Solberg", "Dafna Brark-Erez", "Aharon Barak" };
    }
}