package com.wvkia.tinyioc.aop;

/**
 * 被代理的对象的封装
 * 可以封装一些对被代理对象的封装
 *
 * @author wukai
 * @date 2019/3/14
 */
public class TargetSource {

    private Class<?> targetClass;

    private Class<?>[] interfaces;

    //原始对象
    private Object target;

    public TargetSource(Object target,Class<?> targetClass, Class<?>... interfaces) {
        this.targetClass = targetClass;
        this.interfaces = interfaces;
        this.target = target;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public Object getTarget() {
        return target;
    }
}
