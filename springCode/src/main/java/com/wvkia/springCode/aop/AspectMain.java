package com.wvkia.springCode.aop;

import com.wvkia.springCode.aop.service.HelloAopService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wukai
 * @date 2019/4/3
 */
public class AspectMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop.xml");
        HelloAopService helloAopService = (HelloAopService) applicationContext.getBean("helloAopService");
        helloAopService.sayHello();

    }
}
