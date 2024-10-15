package my.spring;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "my.spring")
public class AppConfig  {

	@Bean
	public Foo foo() {
		return new Foo();
	}

	@Bean
	public Bar bar() {
		return new Bar();
	}
}
