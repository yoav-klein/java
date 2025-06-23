package com.example.web.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.security.access.AccessDeniedException;

import com.example.helpers.Constants;
import com.example.helpers.TenantContext;
import com.example.security.AuthBean;
import com.example.business.exception.NoTenantSelectedException;
@Component
public class TenantInterceptor implements HandlerInterceptor {
    
    @Autowired
    private AuthBean authBean;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AccessDeniedException, NoTenantSelectedException {

        String tenantId = extractTenantIdFromRequest(request);

        if(tenantId != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authBean.isUserPartOfTenant(authentication, tenantId)) TenantContext.setCurrentTenantId(tenantId);
            else throw new AccessDeniedException("User is not part of tenant anymore");
        } else {
            throw new NoTenantSelectedException();
        }
        
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TenantContext.clear(); // Prevent tenant leaks between requests
    }

    private String extractTenantIdFromRequest(HttpServletRequest request) {
        Cookie[] allCookies = request.getCookies();
        
        for(Cookie cookie : allCookies) {
            
            if(cookie.getName().equals(Constants.TENANT_COOKIE_NAME)) {
                return cookie.getValue();
            }
            
        }

        return null;

    }

}
