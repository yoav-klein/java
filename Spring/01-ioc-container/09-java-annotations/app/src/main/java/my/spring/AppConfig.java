package my.spring;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "my.spring")
public class AppConfig  {

	@Bean
	public TCP tcp() {
		return new TCP();
	}

	@Bean
	public HTTPServer httpServer() {
		return new HTTPServer(tcp());
	}
}
