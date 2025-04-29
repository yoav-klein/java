package org.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.foopackage.FooException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // access the return value of the join point
    @AfterReturning(pointcut="execution(Integer *(..))", returning="retVal")
    public void afterReturningInteger(Object retVal) {
        System.out.println("After returning integer");
        System.out.printf("Value: %d%n", (Integer)retVal);
    }

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

    // before any method that receives an Integer
    @Before("execution(* *(Integer)))")
    public void beforeReceivingtInteger2() {
        System.out.println("Before receving integer2");
    }

    @Around("execution(* jazz(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		// do something before
		Object retVal = pjp.proceed();
		// do something after
		return retVal;
	}
}
