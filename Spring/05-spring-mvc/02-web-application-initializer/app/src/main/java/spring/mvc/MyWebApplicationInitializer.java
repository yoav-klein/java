
package spring.mvc;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.WebApplicationInitializer;


public class MyWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) {
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext.setConfigLocation("/WEB-INF/spring-servlet.xml");

		ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet(appContext));
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
	}
}