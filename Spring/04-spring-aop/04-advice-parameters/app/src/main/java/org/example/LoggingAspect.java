package org.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // access the return value returne by the join point method
    @AfterReturning(pointcut="execution(Integer *(..))", returning="retVal")
    public void afterReturningInteger(Object retVal) {
        System.out.println("After returning integer");
        System.out.printf("Value: %d%n", (Integer)retVal);
    }

    // access the JoinPoint representing the join point currently matched
    @After("execution(* seeJoinPoint(..))") 
    public void joinPoint(JoinPoint jp) {
        // get args of joinpoint
        for(Object param : jp.getArgs()) {
            System.out.println(param);
        }
        
        // `this` is the proxy object
        System.out.println(jp.getThis());
        
        // 'target' is the target object
        System.out.println(jp.getTarget());
        
        // JoinPoint implements toString
        System.out.println(jp);
    }

    // access the input parameters using the binding form
    // this will match any method receiving an Integer, and will have access to the passed value
    @Before("args(num)")
    public void seeArgs(Integer num) {
        System.out.println("seeArgs advice, value: " + num);
    }

}
