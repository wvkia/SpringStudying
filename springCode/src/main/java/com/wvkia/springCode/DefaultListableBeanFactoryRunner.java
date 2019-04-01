package com.wvkia.springCode;

import com.wvkia.springCode.service.HelloService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @author wukai
 * @date 2019/3/21
 */
public class DefaultListableBeanFactoryRunner {
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("ioc.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);

        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.hello();

    }
}
