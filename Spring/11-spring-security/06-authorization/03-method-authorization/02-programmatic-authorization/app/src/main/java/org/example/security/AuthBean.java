
package org.example.security;

import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.stereotype.Component;

@Component("authz")
public class AuthBean {
    public boolean decide(MethodSecurityExpressionOperations operations) {
        System.out.println("HEREERE");
        return true;
    }
}
