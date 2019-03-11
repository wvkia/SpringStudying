package com.wvkia.springsource.Container.AnnotationBasedConfiguration_9;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class AnnotationApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/annotationBased/annotationBased.xml");
        MovieRecommender movieRecommender=context.getBean("movieRecommender",MovieRecommender.class);
        System.out.println(movieRecommender);
        System.out.println(movieRecommender.getCatelog());


        //别名
        SimpleCataLogManager manager = context.getBean("simpleCataLogManager", SimpleCataLogManager.class);
        System.out.println(manager.getSimpleCatalog());
        for (Map.Entry<String, SimpleCatalog> entry : manager.getMap().entrySet()) {
            System.out.println(entry.getKey() + "  =  " + entry.getValue());
        }
    }
}
