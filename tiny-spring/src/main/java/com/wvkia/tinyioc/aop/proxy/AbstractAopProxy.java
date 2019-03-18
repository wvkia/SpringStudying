package com.wvkia.tinyioc.aop.proxy;

/**
 * 缺省适配模式
 * 继承AopProxy的接口，有获取代理对象的能力
 * 同时有AdvisedSupport的支持
 * @author wukai
 * @date 2019/3/14
 */
public abstract class AbstractAopProxy implements AopProxy {

    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
