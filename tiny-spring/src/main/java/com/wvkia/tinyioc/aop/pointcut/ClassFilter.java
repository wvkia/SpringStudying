package com.wvkia.tinyioc.aop.pointcut;

/**
 * 类匹配
 *
 * @author wukai
 * @date 2019/3/17
 */
public interface ClassFilter {
    boolean matches(Class targetClass);
}
