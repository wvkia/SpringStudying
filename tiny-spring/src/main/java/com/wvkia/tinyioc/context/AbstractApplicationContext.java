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

    /**
     * 用于实现 BeanFactory 的刷新，也就是告诉BeanFactory该使用哪个资源resource加载Bean的定义BeanDefinition，并实例化，初始化bean
     * @throws Exception
     */
    public void refresh() throws Exception {
        //加载bean的定义并保存到beanFactory中
        loadBeanDefinitions(beanFactory);
        //从BeanFactory中bean的定义中找实现 BeanPostProcessor接口的类（例如：AspectJAwareAdvisorAutoProxyCreator.java）
        //注册到 AbstractBeanFactory 维护的 BeanPostProcessor列表中

        //后面调用getBean方法通过AspectJAwareAdvisorAutoProxyCreator#postProcessorAfterInitialization()方法调用
        //getBeansForType方法保证类 PointcutAdvisor 的实例化顺序优于普通的bean
        registerBeanPostProcessors(beanFactory);

        //默认以单例模式实例化所有bean
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

    //单例模式实现bean
    protected void onRefresh() throws Exception {
        beanFactory.preInstantiatsSingletons();
    }
}
