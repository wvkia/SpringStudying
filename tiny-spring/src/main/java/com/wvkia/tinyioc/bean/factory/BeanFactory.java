package com.wvkia.tinyioc.bean.factory;

/**
 * bean的容器
 * 工厂模式，用来产生一个bean
 *
 * 以BeanFactory接口为核心的几个类，都是用于解决IOC容器在已经获取 Bean 的 定义 情况下
 * 如何装配、获取Bean实例 的问题
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
