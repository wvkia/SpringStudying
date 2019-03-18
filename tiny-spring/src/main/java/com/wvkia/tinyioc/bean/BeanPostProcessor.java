package com.wvkia.tinyioc.bean;

/**
 * bean初始化前后可以做的动作
 * 用于在bean定义初始化时嵌入相关操作
 *  例如：在 postProcessorAfterInitialization 方法中，使用动态代理的方式，返回一个对象的代理对象，就可以在IOC容器中植入AOP
 * @author wukai
 * @date 2019/3/11
 */
public interface BeanPostProcessor {

    /**
     * bean初始化前可以做的动作
     * @param bean 实际的bean
     * @param beanName bean的name
     * @return
     * @throws Exception
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    /**
     * bean初始化后可以做的动作
     * @param bean  实际的bean
     * @param beanName bean的name
     * @return
     * @throws Exception
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
