package com.example.business;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan
@EnableTransactionManagement
public class SpringBusinessConfig implements WebMvcConfigurer {
    public SpringBusinessConfig() {
        super();
    }
    
    @Bean
    @Profile("prod")
    public DataSource dataSource() {
        String dbUrl = "jdbc:mysql://localhost:3306";
        String dbUser = "yoav";
        String dbPassword = "yoav";

        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(dbUrl);
        mysqlDS.setUser(dbUser);
        mysqlDS.setPassword(dbPassword);

        return mysqlDS;
        
    }

    @Bean
    @Profile("test")
    public DataSource testDataSource() {
        return new EmbeddedDatabaseBuilder()
            .generateUniqueName(true)
            .setType(EmbeddedDatabaseType.H2)
            .addScripts("classpath:init.sql")
            .build();
                
    }

}