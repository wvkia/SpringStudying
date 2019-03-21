package com.wvkia.tinyioc.aop.InterceptorAOP;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * 方法拦截器
 * @author wukai
 * @date 2019/3/20
 */
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println("方法执行前方法名 : " + method.getName());
        try {
            //调用Invocation的proceed()
            return invocation.proceed();
        }finally {
            System.out.println("方法执行完成 : "+ method.getName());
        }
    }
}
