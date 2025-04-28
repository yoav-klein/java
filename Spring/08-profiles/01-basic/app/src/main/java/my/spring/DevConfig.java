package my.spring;

import org.springframework.context.annotation.*;

@Profile("dev")
public class DevConfig {
    @Bean
	public DataSource dataSource() {
		return new DevDataSource();
	}
}
