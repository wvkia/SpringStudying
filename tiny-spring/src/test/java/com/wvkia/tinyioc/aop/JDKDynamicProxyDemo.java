package com.wvkia.tinyioc.aop;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * @author wukai
 * @date 2019/3/14
 */
public class JDKDynamicProxyDemo {

   interface MyService{
       void print();
   }

    static class MyServiceImpl implements MyService {
        @Override
        public void print() {
            System.out.println("MyServiceImpl打印数据...");
        }
    }

    //动态代理类
    static class MyInvocationHandler implements InvocationHandler{

       //被代理的实际对象
       private Object proxiedObj;

        public MyInvocationHandler(Object proxiedObj) {
            this.proxiedObj = proxiedObj;
        }

        @Override
        //proxy 代理对象本身，用于反射获取或连续代理
        //method 调用的方法
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("print".equals(method.getName())) {
                System.out.println("代理前做操作...");
                method.invoke(proxiedObj, args);
                System.out.println("代理后操作...");
            }
            //返回代理对象
            return proxy;
        }
    }

    public static void main(String[] args) {
        MyService myService = new MyServiceImpl();
        myService.print();

        //代理
        //创建一个调用处理器，并设置代理对象
        InvocationHandler invocationHandler = new MyInvocationHandler(myService);
        MyService proxyInstance = (MyService) Proxy.newProxyInstance(MyService.class.getClassLoader(),
                myService.getClass().getInterfaces(),//需要代理的接口类
                invocationHandler);//代理处理器
        proxyInstance.print();

        //连续代理
        System.out.println("=======连续代理=============");
        InvocationHandler invocationHandlerSerials = new MyInvocationHandler(proxyInstance);
        proxyInstance = (MyService) Proxy.newProxyInstance(MyService.class.getClassLoader(),
                myService.getClass().getInterfaces(),//需要代理的接口类
                invocationHandlerSerials);//代理处理器
        proxyInstance.print();


    }

}