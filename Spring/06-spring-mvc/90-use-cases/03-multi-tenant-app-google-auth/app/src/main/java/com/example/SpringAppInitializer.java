
package com.example;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.example.business.SpringBusinessConfig;
import com.example.security.SpringSecurityConfig;
import com.example.web.SpringWebConfig;
import jakarta.servlet.Filter;
import org.springframework.web.filter.DelegatingFilterProxy;

public class SpringAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { SpringBusinessConfig.class, SpringSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { SpringWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public Filter[] getServletFilters() {
		DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
		delegatingFilterProxy.setTargetBeanName("springSecurityFilterChain");
		return new Filter[] { delegatingFilterProxy };
	}
}