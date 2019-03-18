package com.wvkia.tinyioc.demo;

import com.wvkia.tinyioc.bean.BeanDefinition;
import com.wvkia.tinyioc.bean.factory.AutowireCapableBeanFactory;
import com.wvkia.tinyioc.bean.factory.BeanFactory;
import com.wvkia.tinyioc.bean.service.HelloWorldService;
import org.junit.Test;

/**
 * 为通过BeanFactory拿到bean的定义
 * @author wukai
 * @date 2019/3/14
 */
public class BeanFactoryDemo {

    @Test
    public  void getBean() throws Exception {
        //1. 初始化beanFactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        //2. 注入bean
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(HelloWorldServiceSPImpl.class.getName());
        ((AutowireCapableBeanFactory) beanFactory).registerBeanDefinition("helloWorldService", beanDefinition);

        //3. 获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.hello();

    }

}
