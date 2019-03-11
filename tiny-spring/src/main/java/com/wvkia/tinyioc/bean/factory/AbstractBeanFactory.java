package com.wvkia.tinyioc.bean.factory;

import com.wvkia.tinyioc.bean.BeanDefinition;
import com.wvkia.tinyioc.bean.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽象类实现接口
 * 封装了对bean获取的基本操作
 * @author wukai
 * @date 2019/3/11
 */
public  abstract class AbstractBeanFactory implements BeanFactory {

    //对象池，根据name作为key存储对应的bean
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    //所有的bean的name
    private final List<String> beanDefinitionNames = new ArrayList<String>();

    //所有bean初始化前后的动作
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    //实现BeanFactory的getBean方法
    @Override
    public Object getBean(String name) throws Exception {
        //从存储map中获取bean的定义
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);

        if (null == beanDefinition) {
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }

        Object bean = beanDefinition.getBean();
        //如果bean的定义中没有，就创建一个
        if (null == bean) {
            bean = doCreateBean(beanDefinition);
            //初始化bean前后动作
            bean = initializeBean(bean, name);
            beanDefinition.setBean(bean);
        }
        return null;
    }

    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception{
        Object bean = beanDefinition.getBeanClass().newInstance();
        beanDefinition.setBean(bean);


        //对bean的属性进行操作
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    //bean的初始化动作，通过调用BeanPostProcessor
    protected Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
    }

    //对bean的属性的操作
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

    }
}
