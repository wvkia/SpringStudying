package com.wvkia.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 实现一个方法切面处理器
 * @author wukai
 * @date 2019/3/17
 */
public class TimerInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        long time = System.currentTimeMillis();
        System.out.println("调用方法 method " + invocation.getMethod().getName() + " start!");

        //方法执行
        Object proceed = invocation.proceed();
        System.out.println("调用方法 " + invocation.getMethod().getName() + " end! 花费时间 " + (System.currentTimeMillis() - time));

        return proceed;
    }
}
