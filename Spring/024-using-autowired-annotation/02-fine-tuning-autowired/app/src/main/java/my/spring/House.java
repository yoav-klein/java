package my.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Set;

public class House {
    @Autowired
    Resident husband;

    @Autowired 
    Resident wife;
    
    @Autowired
    @Qualifier("children")
    Set<Resident> children;

    void presentResidents() {
        System.out.println("Husband name is: " + husband.getName());
        System.out.println("Wife name is: " + wife.getName());

        for (Resident child : children) {
            System.out.println("Child: " + child.getName());
        }
    }
}
