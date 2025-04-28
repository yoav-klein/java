package my.spring;

import org.springframework.context.annotation.*;

@Profile("prod")
public class ProdConfig {
    @Bean
	public DataSource dataSource() {
		return new ProdDataSource();
	}
}
