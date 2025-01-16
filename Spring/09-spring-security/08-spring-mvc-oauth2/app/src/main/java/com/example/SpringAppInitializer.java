
package spring.mvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.Filter;
import com.example.business.SpringBusinessConfig;
import com.example.web.SpringWebConfig;
import org.springframework.web.filter.DelegatingFilterProxy;

public class SpringAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { SpringBusinessConfig.class };
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
		DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy("springSecurityFilterChain");
		delegatingFilterProxy.setTargetBeanName("springSecurityFilterChain");
		delegatingFilterProxy.setTargetFilterLifecycle(true); // Enable lazy initialization
		return new Filter[] { delegatingFilterProxy };
	}
}