package my.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@ComponentScan(basePackages = "my.spring")
public class AppConfig  {
    @Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public MethodValidationPostProcessor validationPostProcessor() {
		MethodValidationPostProcessor processor = new MethodValidationPostProcessor();

		// See https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html#validation-beanvalidation-spring-method-exceptions
		// this is to convert jakarta.validation.ConstraintViolationException that is raised by default to Spring's MethodValidationException 
		processor.setAdaptConstraintViolations(true);
		
		return processor;
	}

	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames("Messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
