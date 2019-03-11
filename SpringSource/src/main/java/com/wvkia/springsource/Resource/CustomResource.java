package com.wvkia.springsource.Resource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomResource {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.wvkia.Resource");
        context.refresh();
        CustomResourceLoader loader = context.getBean(CustomResourceLoader.class);
        String filename = loader.getName("customResource/resource.tld");
        System.out.println(filename);
    }
}
