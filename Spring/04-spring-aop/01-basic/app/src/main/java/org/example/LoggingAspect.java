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
        System.out.println("Before...");
    }

    @After("execution(* *(..))")
    public void afterEach() {
        System.out.println("After...");
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
