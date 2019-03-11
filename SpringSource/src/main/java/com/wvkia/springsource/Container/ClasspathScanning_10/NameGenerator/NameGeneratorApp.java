package com.wvkia.springsource.Container.ClasspathScanning_10.NameGenerator;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//或者使用注解
@Configuration
@ComponentScan(basePackages = "com.wvkia.springsource.annotationBased.NameGenerator",nameGenerator = MyNameGenerator.class)
public class NameGeneratorApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //setBeanNameGenerator必须放到扫描之前
        context.setBeanNameGenerator(new MyNameGenerator());

        context.scan("com.wvkia.annotationBased.NameGenerator");

        context.refresh();
        NameBean bean = context.getBean(NameBean.class);
        String[] names = context.getBeanNamesForType(NameBean.class);
        if (names != null) {
            for (String name : names) {
                System.out.println(name);
            }
        }
        System.out.println(bean);
    }
}
