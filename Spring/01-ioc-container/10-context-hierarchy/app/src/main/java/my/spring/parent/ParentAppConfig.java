package my.spring.parent;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "my.spring.parent")
public class ParentAppConfig  {

	@Bean
	public TCP tcp() {
		return new TCP();
	}

}
