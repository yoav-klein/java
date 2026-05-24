
package org.example;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.example.business.SpringBusinessConfig;
import com.example.security.SpringSecurityConfig;

import com.example.web.SpringWebConfig;

public class SpringAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { SpringBusinessConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {  SpringWebConfig.class, SpringSecurityConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}