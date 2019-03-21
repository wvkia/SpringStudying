package com.wvkia.tinyioc.aop.InterceptorAOP;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author wukai
 * @date 2019/3/20
 */
public class MyInvocation implements MethodInvocation {



    @Override
    public Method getMethod() {
        return null;
    }

    @Override
    public Object[] getArguments() {
        return new Object[0];
    }

    @Override
    public Object proceed() throws Throwable {
        return null;
    }

    @Override
    public Object getThis() {
        return null;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return null;
    }
}
