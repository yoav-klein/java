
package com.example.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurityInitializer extends  AbstractSecurityWebApplicationInitializer {
    
    @Override
    public String getDispatcherWebApplicationContextSuffix() {
      return "dispatcher";
    }
}