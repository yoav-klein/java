
package org.example.security;

import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("authz")
public class AuthBean {
    public boolean decideBefore(Authentication authentication, MethodSecurityExpressionOperations operations) {
        System.out.println("=== BEFORE");
        System.out.println(authentication.getName());
        System.out.println(operations.getReturnObject()); // this should be null  here...
        return true;
    }

    public boolean decideAfter(Authentication authentication, MethodSecurityExpressionOperations operations) {
        System.out.println("=== AFTER");
        System.out.println(operations.getReturnObject());
        return true;
    }
}
