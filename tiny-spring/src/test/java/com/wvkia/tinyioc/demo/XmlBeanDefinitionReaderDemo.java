package com.wvkia.tinyioc.demo;

import com.wvkia.tinyioc.bean.BeanDefinition;
import com.wvkia.tinyioc.bean.factory.AutowireCapableBeanFactory;
import com.wvkia.tinyioc.bean.factory.BeanFactory;
import com.wvkia.tinyioc.bean.io.ResourceLoader;
import com.wvkia.tinyioc.HelloWorldService;
import com.wvkia.tinyioc.bean.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

/**
 * 使用
 * @author wukai
 * @date 2019/3/14
 */
public class XmlBeanDefinitionReaderDemo {

    @Test
    public void testReader() throws Exception {
        //1. 读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("hello.xml");
        //2. 初始化BeanFactory并且注册Bean
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> definitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            //注册bean的name和对应bena的实例
            ((AutowireCapableBeanFactory) beanFactory).registerBeanDefinition(definitionEntry.getKey(), definitionEntry.getValue());
        }

        //3. 获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.hello();
    }

    @Test
    public void testReadReference() throws Exception {
        //1. 读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("ioc.xml");
        //2. 初始化BeanFactory并且注册Bean
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> definitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            //注册bean的name和对应bena的实例
            ((AutowireCapableBeanFactory) beanFactory).registerBeanDefinition(definitionEntry.getKey(), definitionEntry.getValue());
        }

        //3. 获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.hello();
    }
}
