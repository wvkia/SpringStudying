package com.wvkia.springsource.Container.Dependencies_4;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeansApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
        //通过id获取
        Bean bean = context.getBean("bean", Bean.class);
        System.out.println(bean);
        //通过name获取
        Bean bean1 = context.getBean("bean1", Bean.class);
        System.out.println(bean1);

        //拿到是一个对象
        System.out.println(bean == bean1);


        //静态工厂方法
        ClientService clientService = context.getBean("clientService",ClientService.class);
        System.out.println(clientService);

//        BeanFactory beanFactory = new XmlBeanFactory();
    }
}
