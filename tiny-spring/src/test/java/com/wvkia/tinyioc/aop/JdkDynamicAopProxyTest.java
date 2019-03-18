package com.wvkia.tinyioc.aop;

import com.wvkia.tinyioc.HelloWorldService;
import com.wvkia.tinyioc.HelloWorldServiceImpl;
import com.wvkia.tinyioc.aop.proxy.AdvisedSupport;
import com.wvkia.tinyioc.aop.proxy.JdkDynamicAopProxy;
import com.wvkia.tinyioc.context.ApplicationContext;
import com.wvkia.tinyioc.context.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author wukai
 * @date 2019/3/18
 */
public class JdkDynamicAopProxyTest {
    @Test
    public void test() throws Exception {
        //helloService without AOP

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.hello();



        //helloWorldService with AOP

        //1. 设置被代理对象(Jointpoint)
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class, HelloWorldService.class);
        advisedSupport.setTargetSource(targetSource);


        //2. 设置拦截器 （Advice）
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);


        //3. 创建代理（Proxy）
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();

        //4. 基于AOP的调用
        helloWorldServiceProxy.hello();

    }
}
