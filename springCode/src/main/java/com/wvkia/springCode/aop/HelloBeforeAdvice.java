package com.wvkia.springCode.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author wukai
 * @date 2019/3/21
 */
public class HelloBeforeAdvice  implements MethodBeforeAdvice {

    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Before : " + method.getName() + ", Args : " + args + " ,实际对象 : " + target);
    }
}
