package com.wvkia.tinyioc.aop.pointcut;

import com.wvkia.tinyioc.aop.pointcut.ClassFilter;
import com.wvkia.tinyioc.aop.pointcut.MethodMatcher;

/**
 * 切点接口
 * @author wukai
 * @date 2019/3/17
 */
public interface Pointcut {

    //返回类适配类
    ClassFilter getClassFilter();

    //返回方法适配类
    MethodMatcher getMethodMatcher();
}
