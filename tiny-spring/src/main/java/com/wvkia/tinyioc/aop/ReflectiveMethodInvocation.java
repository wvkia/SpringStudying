package com.wvkia.tinyioc.aop;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * 反射方法调用工具类
 * @author wukai
 * @date 2019/3/14
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    protected Object target;

    protected Method method;

    protected Object[] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    /**
     * 调用被拦截对象的方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object proceed() throws Throwable {
        /**
         * 这里直接调用target原始对象的方法，如果想要支持拦截器链，可以改为调用嗲里对象的方法 method.invoke(proxy,args)
         * 在代理对象的InvocationHandler的invoke函数中，查看拦截器列表，如果有拦截器，调用第一个拦截器并返回否则调用原始对象方法
         */
        return method.invoke(target, arguments);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
