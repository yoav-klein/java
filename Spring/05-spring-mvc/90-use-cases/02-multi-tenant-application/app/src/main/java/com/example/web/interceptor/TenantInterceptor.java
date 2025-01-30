package com.example.web.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.helpers.Constants;
import com.example.helpers.TenantContext;

public class TenantInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] allCookies = request.getCookies();
        
        for(Cookie cookie : allCookies) {
            
            if(cookie.getName().equals(Constants.TENANT_COOKIE_NAME)) {
            
                TenantContext.setCurrentTenantId(cookie.getValue());
            }
        }

        return true;
    }
}
