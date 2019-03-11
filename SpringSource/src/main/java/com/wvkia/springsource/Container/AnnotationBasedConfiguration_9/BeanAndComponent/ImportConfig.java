package com.wvkia.springsource.Container.AnnotationBasedConfiguration_9.BeanAndComponent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportConfig {
    @Bean
    public Bean1 getBean1(){
        return new Bean1();
    }
}
