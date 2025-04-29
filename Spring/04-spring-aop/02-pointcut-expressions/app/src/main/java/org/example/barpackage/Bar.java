package org.example.barpackage;

import org.springframework.stereotype.Component;

@Component
public class Bar {
    
    
    public Integer returningInteger() {
        System.out.println("Returning integer");
        return Integer.valueOf(200);
    }

    public void receivingInteger(Integer i) {
        System.out.println("receiving integer");
    }

    public void receivingIntegerAndMoreStuff(Integer i, String s) {
        System.out.println("receiving integer and more stuff");
    }

    public String jazz(String s) {
        System.out.println("in jazz");
        System.out.println(s);
        return "Hello from jazz";
    }

    
}
