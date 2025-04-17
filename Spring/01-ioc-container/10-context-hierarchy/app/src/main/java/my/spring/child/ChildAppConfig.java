package my.spring.child;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import my.spring.parent.TCP;

@Configuration
@ComponentScan(basePackages = "my.spring.child")
public class ChildAppConfig  {

	@Autowired
	TCP tcp;

	@Bean
	public HTTPServer httpServer() {
		return new HTTPServer(tcp);
	}
}
