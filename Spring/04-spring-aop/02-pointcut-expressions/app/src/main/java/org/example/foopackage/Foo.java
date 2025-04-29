package org.example.foopackage;

import org.springframework.stereotype.Component;

@Component
public class Foo {

    public void sayHi() {
        System.out.println("sayHi");
    }
    

    // for advice that only catch get* methods
    public void getGoing() {
        System.out.println("foo getGoing");
    }

}
