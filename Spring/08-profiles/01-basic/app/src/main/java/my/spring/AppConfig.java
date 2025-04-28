package my.spring;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "my.spring")
public class AppConfig  {

	@Bean
	@Profile("prod")
	public DataSource prodDataSource() {
		return new ProdDataSource();
	}

	@Bean
	@Profile("dev")
	public DataSource devDataSource() {
		return new DevDataSource();
	}
}
