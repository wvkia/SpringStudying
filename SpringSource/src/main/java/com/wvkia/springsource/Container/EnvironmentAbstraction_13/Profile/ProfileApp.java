package com.wvkia.springsource.Container.EnvironmentAbstraction_13.Profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Map;

@Configuration
public class ProfileApp {
    @Bean
    @Profile("dev")
    public DataSource devDataSource(){
        return new DataSource0();
    }
    @Bean
    @Profile("production")
    public DataSource productDataSource(){
        return new DataSource1();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProfileApp.class);
        context.getEnvironment().setActiveProfiles("dev");
        Map<String, DataSource> dataSource = context.getBeansOfType(DataSource.class);
        for (String key : dataSource.keySet()) {
            System.out.println(key + "  " + dataSource.get(key));
        }
    }
}
