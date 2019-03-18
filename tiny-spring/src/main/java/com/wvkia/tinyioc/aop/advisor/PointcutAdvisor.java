package com.wvkia.tinyioc.aop.advisor;

import com.wvkia.tinyioc.aop.pointcut.Pointcut;

/**
 *切点通知器
 * @author wukai
 * @date 2019/3/17
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * 获取切点
     * @return
     */
    Pointcut getPointcut();
}
