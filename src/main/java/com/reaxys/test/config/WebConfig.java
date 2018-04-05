package com.reaxys.test.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class WebConfig {
    private final static String SOURCE = "additionsToFilter.properties";

    @Bean(name = "additionsToFilter")
    public PropertiesFactoryBean mapper() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource(SOURCE));
        return bean;
    }


}
