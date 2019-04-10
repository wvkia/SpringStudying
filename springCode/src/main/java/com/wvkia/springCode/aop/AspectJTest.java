package com.wvkia.springCode.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author wukai
 * @date 2019/4/3
 */
@Aspect
public class AspectJTest {

    @Pointcut("execution(* *.sayHello())")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before() {
        System.out.println("Before === ");

    }



    @After("pointcut()")
    public void after() {
        System.out.println("After === ");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint p) {
        System.out.println("Before around-");
        Object o = null;
        try {
            o = p.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("After around-");
        return o;
    }
}
