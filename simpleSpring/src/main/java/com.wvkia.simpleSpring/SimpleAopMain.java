package com.wvkia.simpleSpring;

import com.wvkia.simpleSpring.model.HelloService;
import com.wvkia.simpleSpring.model.HelloServiceImpl;

/**
 * @author wukai
 * @date 2019/4/9
 */
public class SimpleAopMain {
    public static void main(String[] args) {
        MethodInvocation methodInvocation=new MethodInvocation() {
            public void invoke() {
                System.out.println("execute ...");

            }
        };

        HelloService helloService = new HelloServiceImpl();
        Advice advice = new BeforeAdvice(helloService, methodInvocation);

        HelloService proxy = (HelloService) SimpleAOP.getProxy(helloService, advice);
        proxy.hello();
    }
}
