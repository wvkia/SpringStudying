package com.wvkia.simpleSpring;

import java.lang.reflect.Proxy;

/**
 * @author wukai
 * @date 2019/4/9
 */
public class SimpleAOP {
    public static Object getProxy(Object bean, Advice advice) {
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(), bean.getClass().getInterfaces(), advice);
    }
}
