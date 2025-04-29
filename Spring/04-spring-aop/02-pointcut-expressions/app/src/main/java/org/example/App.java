package org.example;

import org.example.barpackage.Bar;
import org.example.foopackage.Foo;
import org.example.foopackage.KongFoo;
import org.example.foopackage.service.FooService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    
    public static void main(String[] args) {
        
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Foo foo = (Foo) context.getBean("foo");
        Bar bar = (Bar) context.getBean("bar");
        KongFoo kf = (KongFoo) context.getBean("kongFoo");
        FooService fooService = (FooService) context.getBean("fooService");
        foo.sayHi();
        foo.getGoing();
        kf.kongFoo();
        fooService.fooService();
        bar.returningInteger();
        bar.receivingInteger(Integer.valueOf(3));
        bar.receivingIntegerAndMoreStuff(Integer.valueOf(3), "Hello world");
        System.out.println(bar.jazz("Hello from main"));
    }
}