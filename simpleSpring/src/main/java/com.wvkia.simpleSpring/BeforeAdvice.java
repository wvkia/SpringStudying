package com.wvkia.simpleSpring;

import java.lang.reflect.Method;

/**
 * @author wukai
 * @date 2019/4/9
 */
public class BeforeAdvice implements Advice {
    private Object source;


    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object source, MethodInvocation methodInvocation) {
        this.source = source;
        this.methodInvocation = methodInvocation;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //目标方法执行前执行advice
        methodInvocation.invoke();


        return method.invoke(source, args);
    }
}
