package com.wvkia.tinyioc.demo;

import com.wvkia.tinyioc.HelloWorldService;
import com.wvkia.tinyioc.context.ApplicationContext;
import com.wvkia.tinyioc.context.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author wukai
 * @date 2019/3/17
 */
public class ApplicationContextDemo {

    @Test
    public  void test() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("hello.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.hello();
    }

    @Test
    public void testBeanProcessor() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.hello();
    }
}
