package org.example.business.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class FooService {
    
    @PreAuthorize("hasRole('ADMIN')")
    public void someImportantMethod() {
        System.out.println("Important method");
    }
}
