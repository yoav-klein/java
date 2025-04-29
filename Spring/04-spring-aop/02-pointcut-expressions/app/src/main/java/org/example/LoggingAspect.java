package org.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* *(..))")
    public void beforeEach() {
        System.out.println("Before each method");
    }
    
    // before any method that starts with get
    @Before("execution(* get*(..))")
    public void beforeEachGet() {
        System.out.println("Before get");
    }

    @After("execution(* *(..))")
    public void afterEach() {
        System.out.println("After each method");
    }

    // after each execution of method in the org.example.foopackage package
    @After("execution(* org.example.foopackage.*.*(..))")
    public void afterAllFoo() {
        System.out.println("After each foopackage");
    }

    // after each execution of method in the org.example.foopackage and subpackages
    @After("execution(* org.example.foopackage..*.*(..))")
    public void afterAllFooAndSubpackage() {
        System.out.println("After each foopackage and subpackage of");
    }

    // after any method that returns an Integer
    @After("execution(Integer *(..))")
    public void afterReturningInteger() {
        System.out.println("After returning integer");
    }

    // before any method that receives an Integer
    @Before("args(Integer)")
    public void beforeReceivingtInteger() {
        System.out.println("Before receving integer");
    }

    // before any method that receives an Integer
    @Before("execution(* *(Integer)))")
    public void beforeReceivingtInteger2() {
        System.out.println("Before receving integer2");
    }

    @Around("execution(* *(..))")
    public void around(ProceedingJoinPoint pjp) {
        System.out.println("Around: before");
        try {
            pjp.proceed();
        } catch(Throwable e) {
            System.out.println("Exception: " + e);
        }
        System.out.println("Around: after");
    }
}
