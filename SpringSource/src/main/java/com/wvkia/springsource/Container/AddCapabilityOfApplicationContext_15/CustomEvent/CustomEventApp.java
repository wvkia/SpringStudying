package com.wvkia.springsource.Container.AddCapabilityOfApplicationContext_15.CustomEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomEventApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.wvkia.Container.AddCapabilityOfApplicationContext_15.CustomEvent");
        context.refresh();
        CustomPublisher publisher = context.getBean(CustomPublisher.class);

        //发布者dosomething
        publisher.doSomething("你好");

    }
}
