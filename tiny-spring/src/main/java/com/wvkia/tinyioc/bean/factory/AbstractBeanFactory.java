package com.wvkia.tinyioc.bean.factory;

import com.wvkia.tinyioc.bean.BeanDefinition;
import com.wvkia.tinyioc.bean.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * BeanFactory 抽象类实现接口，规范了 IOC 容器的基本结构
 * 封装了对bean获取的基本操作
 *
 * 通过维护一个 beanDefinitionMap 哈希表来保存类的定义信息(BeanDefinition)
 * @author wukai
 * @date 2019/3/11
 */
public  abstract class AbstractBeanFactory implements BeanFactory {


    /**
     * 对象池，根据name作为key存储对应的bean的定义
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();


    /**
     * 保存完成注册的bean的name
     */
    private final List<String> beanDefinitionNames = new ArrayList<String>();

    /**
     * 增加bean的处理程序
     */
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
            //装配bean（实例化并注入属性）
            bean = doCreateBean(beanDefinition);
            //初始化bean
            //例如：生成代理类，用于AOP织入
            bean = initializeBean(bean, name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    //装配bean
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception{

        //实例化bean
        Object bean = beanDefinition.getBeanClass().newInstance();
        beanDefinition.setBean(bean);


        //对bean的属性进行操作
        //注入属性的hook方法，交给子类去实现
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    /**
     * bean的初始化动作，通过调用BeanPostProcessor
     * 初始化bean
     *
     * 可以在此进行AOP的相关调用：例如生成代理类
     * @param bean
     * @param name
     * @return
     * @throws Exception
     */
    protected Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }

        //返回的可能是代理对象
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
    }

    //对bean的属性的操作，通常是用来注入bean的属性引用的
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

    }


    //注册bean的定义
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }


    /**
     * 预处理bean的定义
     * @throws Exception
     */
    public void preInstantiatsSingletons() throws Exception {
        //将bean的实例给初始化
        for (String definitionName : beanDefinitionNames) {
            getBean(definitionName);
        }
    }
    //添加bean的处理动作
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 根据类型获取所有bean的实例
     * @param type
     * @return
     * @throws Exception
     */
    public List getBeansForType(Class type) throws Exception {
        List beans = new ArrayList();
        for (String name : beanDefinitionNames) {

            /**
             * boolean isAssignableFrom(Class class)
             * 判定此Class 对象所表示的类或接口与指定的 Class 参数所表示类或接口相同，或是其超类
             */
            if (type.isAssignableFrom(beanDefinitionMap.get(name).getBeanClass())) {
                beans.add(getBean(name));
            }
        }
        return beans;
    }



}
