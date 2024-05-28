package my.spring;

import org.springframework.context.annotation.*;
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
		return new MethodValidationPostProcessor();
	}
}
