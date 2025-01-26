package com.example.business;

import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@ComponentScan
public class SpringBusinessConfig implements WebMvcConfigurer {
    public SpringBusinessConfig() {
        super();
    }

    @Bean("systemDataSource")
    public DataSource systemDataSource() {
        String dbUrl = "jdbc:mysql://localhost:3306/tenant_system";
        String dbUser = "yoav";
        String dbPassword = "yoav";

        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(dbUrl);
        mysqlDS.setUser(dbUser);
        mysqlDS.setPassword(dbPassword);

        return mysqlDS;
        
    }

    @Bean("generalDataSource")
    public DataSource generalDataSource() {
        String dbUrl = "jdbc:mysql://localhost:3306";
        String dbUser = "yoav";
        String dbPassword = "yoav";

        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(dbUrl);
        mysqlDS.setUser(dbUser);
        mysqlDS.setPassword(dbPassword);

        return mysqlDS;
        
    }
}