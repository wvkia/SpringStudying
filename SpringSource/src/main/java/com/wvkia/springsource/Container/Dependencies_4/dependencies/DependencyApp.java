package com.wvkia.springsource.Container.Dependencies_4.dependencies;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependencies/dependencies.xml");
        Foo foo=context.getBean("foo", Foo.class);
        System.out.println(foo);
    }
}
