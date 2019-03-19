package com.wvkia.springCode;

import com.wvkia.springCode.service.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wukai
 * @date 2019/3/19
 */
public class ApplicationContextRunner {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc.xml");
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        helloService.hello();

    }
}
