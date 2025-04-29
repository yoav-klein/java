package org.example.foopackage;

import org.springframework.stereotype.Component;

@Component
public class Foo {    
    // for advice that only catch get* methods
    public void getInteger(Integer value) {
        System.out.println("foo getInteger");
    }

    public Integer returnInteger() {
        return Integer.valueOf(200);
    }

    public void throwsFooException() throws FooException {
        System.out.println("in throwsFooException, before throwing exception");
        throw new FooException();
    }

    public void throwsException() throws Exception {
        System.out.println("in throwsException, before throwing exception");
        throw new Exception();
    }

    public String jazz(String s) {
        System.out.println("Value of s: " + s);
        return "Hello from jazz";
    }

}
