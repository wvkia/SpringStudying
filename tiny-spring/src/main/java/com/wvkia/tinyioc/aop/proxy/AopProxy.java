package com.wvkia.tinyioc.aop.proxy;

/**
 * AopProxy是个标志性接口
 * 暴漏获取aop代理对象方法的接口
 * @author wukai
 * @date 2019/3/14
 */
public interface AopProxy {

    /**
     * 获取代理对象
     * @return
     */
    Object getProxy();
}
