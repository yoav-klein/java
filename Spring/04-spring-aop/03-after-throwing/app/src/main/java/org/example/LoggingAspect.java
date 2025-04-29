package org.example;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.example.foopackage.FooException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {


    // matches any join point that throws an exception
    @AfterThrowing("execution(* *(..))")
    public void afterThrowing() {
        System.out.println("AfterThrowing any exception");
    }

    // matches any join point that throws FooException
    @AfterThrowing(pointcut="execution(* *(..))", throwing="ex")
    public void afterThrowingFoo(FooException ex) {
        System.out.println("AfterThrowing FooException");
    }
}
