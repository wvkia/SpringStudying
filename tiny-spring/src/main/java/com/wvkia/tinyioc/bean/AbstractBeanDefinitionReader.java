package com.wvkia.tinyioc.bean;

import com.wvkia.tinyioc.bean.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 缺省适配模式
 * @author wukai
 * @date 2019/3/11
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    //Bean的注册存储
    private Map<String, BeanDefinition> registry;


    //资源加载器
    private ResourceLoader resourceLoader;


    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.registry = new HashMap<>();
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
