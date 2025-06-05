package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:org/example/app.properties")
public class AppConfig {

    @Autowired
    Environment env;
    
    @Bean
    TestBean testBean() {
        return new TestBean(env.getProperty("name"));
    }
}
