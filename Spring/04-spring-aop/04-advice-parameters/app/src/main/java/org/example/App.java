package org.example;

import org.example.foopackage.Foo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    
    public static void main(String[] args) {
        
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Foo foo = (Foo) context.getBean("foo");
        foo.returningInteger();
        foo.seeJoinPoint("one", "two");
        foo.gettingInteger(Integer.valueOf(33));
    }
}