package com.wvkia.springCode.extra;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author wukai
 * @date 2019/4/1
 */
public class FactoryBeanRunner {
    public static void main(String[] args) {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("ioc.xml"));

        //1. 获取bean
        FactoryBeanModel factoryBeanModel = (FactoryBeanModel) beanFactory.getBean("factoryBeanModel");
        System.out.println(factoryBeanModel);
        System.out.println(factoryBeanModel.getId());

        //再获取一次
        FactoryBeanModel factoryBeanModel_1 = (FactoryBeanModel) beanFactory.getBean("factoryBeanModel");
        System.out.println(factoryBeanModel_1);
        System.out.println(factoryBeanModel==factoryBeanModel_1);

        //2. 获取factoryBean
        MyFactoryBean myFactoryBean = (MyFactoryBean) beanFactory.getBean("&factoryBeanModel");
        System.out.println(myFactoryBean);

        beanFactory.getBean("&helloService");
    }
}
