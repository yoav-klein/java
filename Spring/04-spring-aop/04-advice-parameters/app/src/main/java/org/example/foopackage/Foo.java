package org.example.foopackage;

import org.springframework.stereotype.Component;

@Component
public class Foo {

    public Integer returningInteger() {
        System.out.println("returning integer");
        return Integer.valueOf(200);
    }

    public void seeJoinPoint(String a, String b) {
        System.out.println("seeJoinPoint");
    }

    public void gettingInteger(Integer i) {
        System.out.println("getting integer");
        System.out.println("Value: " + i);
    }
    

}
