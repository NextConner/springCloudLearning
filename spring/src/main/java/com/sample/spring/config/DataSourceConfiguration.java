package com.sample.spring.config;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Component
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource()throws Exception{
        Properties properties = new Properties();
        properties.setProperty("driverClassName","org.h2.Driver");
        properties.setProperty("url","jdbc:h2:mem:testdb");
        properties.setProperty("username","sa");
        return BasicDataSourceFactory.createDataSource(properties);
    }

}
