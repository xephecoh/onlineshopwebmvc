package com.khamutov.configuration;

import com.khamutov.configuration.PropertiesReader;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

@Configuration
public class AppConfig {
    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setCache(true);
        resolver.setSuffix(".html");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/view");
        return freeMarkerConfigurer;
    }

    @Bean
    public PGSimpleDataSource pgSimpleDataSource() {
        PropertiesReader propertiesReader = new PropertiesReader("target/classes/application.properties");
        Properties properties = propertiesReader.getProperties();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(properties.getProperty("postgres.url"));
        dataSource.setUser(properties.getProperty("postgres.username"));
        dataSource.setPassword(properties.getProperty("postgres.password"));
        return dataSource;
    }
}
