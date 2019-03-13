package com.wvkia.tinyioc.context;

import com.wvkia.tinyioc.bean.BeanPostProcessor;
import com.wvkia.tinyioc.bean.factory.AbstractBeanFactory;

import java.util.List;

/**
 * 缺省适配模式
 * @author wukai
 * @date 2019/3/12
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    //bean工厂,applicationContext实际上是使用了BeanFactory，只不过是在BeanFactory基础上进行了封装其他的方法
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


    //实现getBean
    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

    //刷新上下文
    public void refresh() throws Exception {
        //重新加载bean定义
        loadBeanDefinitions(beanFactory);
        //注册bean处理接口
        registerBeanPostProcessors(beanFactory);
        onRefresh();
    }

    //重新从BeanFactory加载bean
    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;


    //注册bean的处理接口
    protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }

    protected void onRefresh() throws Exception {
        beanFactory.preInstantiatsSingletons();
    }
}
