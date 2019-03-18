package com.wvkia.tinyioc.aop.proxy;

/**
 * 缺省适配模式
 * @author wukai
 * @date 2019/3/14
 */
public abstract class AbstractAopProxy implements AopProxy {

    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
