package com.wvkia.tinyioc.aop.pointcut;

/**
 * 切点，确定是对什么类的什么方法进行AOP（就是确定在哪切）
 * 相当于查询条件，一个切点可以匹配多个连接点
 * @author wukai
 * @date 2019/3/17
 */
public interface Pointcut {

    /**
     * 获取 ClasFilter，类名匹配（筛选要代理的目标对象）
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * 获取一个MethodMatcher对象
     * @return
     */
    MethodMatcher getMethodMatcher();
}
