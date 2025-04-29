package org.example.foopackage;

import org.springframework.stereotype.Component;

@Component
public class Foo {    
    
    public void throwsFooException() throws FooException {
        System.out.println("in throwsFooException, before throwing exception");
        throw new FooException();
    }

    public void throwsException() throws Exception {
        System.out.println("in throwsException, before throwing exception");
        throw new Exception();
    }

}
