package com.wvkia.tinyioc.aop.pointcut;

import java.lang.reflect.Method;

/**
 * 用于适配方法的挑选器
 * 方法匹配
 * @author wukai
 * @date 2019/3/14
 */
public interface MethodMatcher {
    boolean matches(Method method, Class targetClass);
}
