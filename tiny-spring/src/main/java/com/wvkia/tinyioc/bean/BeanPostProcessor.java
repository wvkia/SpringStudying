package com.wvkia.tinyioc.bean;

/**
 * bean初始化前后可以做的动作
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
