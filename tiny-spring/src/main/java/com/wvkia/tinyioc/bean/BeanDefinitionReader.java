package com.wvkia.tinyioc.bean;

/**
 * 从配置中读取BeanDefinition的接口
 * @author wukai
 * @date 2019/3/11
 */
public interface BeanDefinitionReader {
    //从location中加载bean的定义
    void loadBeanDefinitions(String location) throws Exception;
}
