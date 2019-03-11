package com.wvkia.tinyioc.bean.factory;

/**
 * bean的容器
 * 工厂模式，用来产生一个bean
 * @author wukai
 * @date 2019/3/11
 */
public interface BeanFactory {
    /**
     * 根据name获取一个bean
     * @param name bean的name
     * @return
     * @throws Exception
     */
    Object getBean(String name) throws Exception;
}
