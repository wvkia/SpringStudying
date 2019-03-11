package com.wvkia.springsource.Container.AnnotationBasedConfiguration_9.Component;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CompoApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext();
        ((AnnotationConfigApplicationContext) context).scan("com.wvkia.annotationBased.Component");
        ((AnnotationConfigApplicationContext) context).refresh();
        Compo compo = context.getBean(Compo.class);
        System.out.println(compo);


        Example example = context.getBean(Example.class);
        System.out.println(example);
    }
}
