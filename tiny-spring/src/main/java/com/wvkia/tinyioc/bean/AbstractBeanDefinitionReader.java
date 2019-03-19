package com.wvkia.tinyioc.bean;

import com.wvkia.tinyioc.bean.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 缺省适配模式
 * 实现 BeanDefinitionReader 接口的抽象类，规范类BeanDefinitionReader的基本结构
 * @author wukai
 * @date 2019/3/11
 */
public abstract class AbstractBeanDefinitionReader extends ResourceLoader implements BeanDefinitionReader {

    //Bean的注册存储
    private Map<String, BeanDefinition> registry;


    /**
     * 解析BeanDefinition保存到 registry 中
     */
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
