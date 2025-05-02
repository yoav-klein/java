package org.example.business.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class FooService {
    
    @PreAuthorize("@authz.decide(#root)")
    public void someImportantMethod() {
        System.out.println("Important method");
    }
}
