package com.example.web.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.helpers.Constants;
import com.example.helpers.TenantContext;
import com.example.business.exception.NoTenantSelectedException;

public class TenantInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tenantId = extractTenantIdFromRequest(request);

        if(tenantId != null) {
            TenantContext.setCurrentTenantId(tenantId);
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
