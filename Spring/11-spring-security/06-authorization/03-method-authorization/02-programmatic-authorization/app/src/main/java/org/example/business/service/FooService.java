package org.example.business.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class FooService {
    
    @PreAuthorize("@authz.decideBefore(authentication, #root)")
    @PostAuthorize("@authz.decideAfter(authentication, #root)")
    public String someImportantMethod() {
        System.out.println("Important method");
        return "kuku";
    }
}
