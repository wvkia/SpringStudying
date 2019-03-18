package com.wvkia.tinyioc.context;

import com.wvkia.tinyioc.bean.factory.BeanFactory;

/**
 * 以 ApplicationContext为接口的核心发散的几个类，主要是对前面的 Resource、BeanFactory、BeanDefinition进行功能的封装，
 * 解决 根据地址获取资源通过 IOC容器注册bean定义并实例化bean的问题
 *
 * 通常，要实现一个IOC容器，需要先通过 ResourceLoarder 获取一个resource，其中包含类容器的配置、Bean的定义信息。接着，使用 BeanDefinitionReader接口
 * 读取并注册该 Resource 中的 BeanDefinition信息。最后将 BeanDefinition 保存在BeanFactory中，容器配置完毕
 * @author wukai
 * @date 2019/3/12
 */
public interface ApplicationContext extends BeanFactory {
}
