package com.wvkia.springsource.Container.EnvironmentAbstraction_13.PropertyEnv;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/annotationBased/my.properties")
public class PropertyEnvApp {
    public static void main(String[] args) {
        ApplicationContext context = new GenericApplicationContext();
        Environment environment = context.getEnvironment();
        boolean foocontain = environment.containsProperty("foo");
        System.out.println(foocontain);

        context = new AnnotationConfigApplicationContext(PropertyEnvApp.class);
        Environment environmentPro = context.getEnvironment();
        System.out.println(environmentPro.getProperty("a.value"));
    }
}
