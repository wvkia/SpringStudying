package com.wvkia.springsource.Container.ContainerExtension;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "com.wvkia.Container.ContainerExtension")
public class ContainerExtensionApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContainerExtensionApp.class);
    }
}
