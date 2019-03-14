package com.wvkia.tinyioc.aop;

import com.wvkia.tinyioc.bean.factory.BeanFactory;

/**
 * 注入BeanFactory
 * @author wukai
 * @date 2019/3/14
 */
public interface BeanFactoryAware {
    //注入BeanFactory
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
