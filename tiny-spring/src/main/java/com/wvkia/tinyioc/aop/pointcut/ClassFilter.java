package com.wvkia.tinyioc.aop.pointcut;

/**
 * 类匹配器
 *
 * @author wukai
 * @date 2019/3/17
 */
public interface ClassFilter {

    /**
     * 用于匹配targetClass是否是要拦截的类
     * @param targetClass
     * @return
     */
    boolean matches(Class targetClass);
}
