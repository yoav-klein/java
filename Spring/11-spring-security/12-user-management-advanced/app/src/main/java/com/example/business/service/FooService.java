package com.example.business.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.security.core.parameters.P;

@Service
public class FooService {
    
    @PreAuthorize("hasRole('ADMIN')")
    public void someImportantMethod() {
        System.out.println("Important method");
    }

    @PreAuthorize("#requestedId == principal.appUser.id")
    public void getUserPrivateData(@P("requestedId") String id) {
        System.out.println("GETTING PRIVATE DATA OF USER");
    }
}
