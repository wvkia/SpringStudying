package com.wvkia.springCode.aop;

import com.wvkia.springCode.aop.service.HelloAopService;
import com.wvkia.springCode.aop.service.HelloAopServiceImpl;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactoryBean;

/**
 * @author wukai
 * @date 2019/3/21
 */
public class HelloAOPMain {
    public static void main(String[] args) {
        //1. 设置不同的advice
        Advice beforeAdvice = new HelloBeforeAdvice();
        Advice aroundAdvice = new HelloAroundAdvice();

        HelloAopService helloAopService = new HelloAopServiceImpl();

        //2. 设置ProxyFactoryBean，用来创建指定对象的代理Proxy对象
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        //设置接口
        proxyFactoryBean.setInterfaces(helloAopService.getClass().getInterfaces());
        //设置真实对象RealSubject
        proxyFactoryBean.setTarget(helloAopService);
        //使用JDK基于接口实现机制的动态代理生成Proxy代理对象，如果想使用CGLIB，需要将这个flag设置成true
        proxyFactoryBean.setProxyTargetClass(true);

        //3. 添加advice
        proxyFactoryBean.addAdvice(beforeAdvice);
        proxyFactoryBean.addAdvice(aroundAdvice);

        //4. 获取代理对象ProxySubject
        HelloAopService proxyService = (HelloAopService) proxyFactoryBean.getObject();
        proxyService.sayHello();

        System.out.println("=============");

        proxyService.sayBye();

    }
}
