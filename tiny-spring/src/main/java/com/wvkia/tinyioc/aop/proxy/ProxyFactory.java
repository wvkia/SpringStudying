package com.wvkia.tinyioc.aop.proxy;

/**
 * 工厂模式
 * @author wukai
 * @date 2019/3/17
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {


    @Override
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    protected final AopProxy createAopProxy() {
        return new Cglib2AopProxy(this);
    }
}
