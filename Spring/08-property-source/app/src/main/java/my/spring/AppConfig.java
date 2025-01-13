package my.spring;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "my.spring")
public class AppConfig  {
	// the values can be retrieved using the Environment
	@Autowired
    Environment env;

	// or by using the @Value annotation
	@Value("${username}")
	private String username;

	@Value("${password}")
	private String password;


	public void printDbSettings() {
		System.out.println(env.getProperty("driverClass"));
		System.out.println(username);
		System.out.println(password);
		System.out.println(env.getProperty("url"));

	}
	// ...
}
