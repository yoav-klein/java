package org.example.business.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class FooService {
    
    // the #root is a concept in SpEL, and it refers to the root context object.
    // See https://docs.spring.io/spring-framework/reference/core/expressions/language-ref/variables.html
    
    @PreAuthorize("@authz.decideBefore(authentication, #root)")
    @PostAuthorize("@authz.decideAfter(authentication, #root)")
    public String someImportantMethod() {
        System.out.println("Important method");
        return "kuku";
    }
}
