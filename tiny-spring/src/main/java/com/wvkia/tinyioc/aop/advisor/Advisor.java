package com.wvkia.tinyioc.aop.advisor;

import org.aopalliance.aop.Advice;

/**
 * 获取一个切面
 * @author wukai
 * @date 2019/3/17
 */
public interface Advisor {
    //获取切面
    Advice getAdvice();
}
