package com.wvkia.springsource.AOP.AspectJ.pointcuts;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemArchitecture {

    //在AspectJ包以及字包
    @Pointcut("within(com.wvkia.springsource.AOP.AspectJ..*)")
    public void inWebLayer(){}

    //匹配任何public方法
    @Pointcut("execution(public * * (..))")
    public void anyPublicOpearion(){}
    
}
