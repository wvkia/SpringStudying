package com.wvkia.springCode.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author wukai
 * @date 2019/3/21
 */
public class HelloAroundAdvice implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Around 前 :" + invocation.getMethod().getName());
        Object value = invocation.proceed();
        System.out.println("Around 后 :" + invocation.getMethod().getName());

        return value;
    }
}
