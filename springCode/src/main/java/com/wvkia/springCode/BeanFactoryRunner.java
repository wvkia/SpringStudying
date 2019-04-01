package com.wvkia.springCode;

import com.wvkia.springCode.service.HelloService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author wukai
 * @date 2019/3/21
 */
public class BeanFactoryRunner {
    public static void main(String[] args) {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("ioc.xml"));
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.hello();

    }
}
