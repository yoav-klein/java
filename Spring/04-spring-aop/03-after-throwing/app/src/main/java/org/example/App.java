package org.example;

import org.example.foopackage.Foo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    
        try {
            foo.throwsException();
        } catch(Exception e) {
            System.out.println("caught");
        }

        try {
            foo.throwsFooException();
        } catch(Exception e) {
            System.out.println("caught");
        }

    }
}