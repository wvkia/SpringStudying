package com.wvkia.tinyioc.aop.advisor;

import org.aopalliance.aop.Advice;

/**
 * 通知器
 * 用于实现 具体的方法拦截，需要使用者编写，也对应了Spring的前置通知、后置通知、环绕通知
 * @author wukai
 * @date 2019/3/17
 */
public interface Advisor {

    /**
     * 获取通知器（方法拦截器）
     * @return
     */
    Advice getAdvice();
}
