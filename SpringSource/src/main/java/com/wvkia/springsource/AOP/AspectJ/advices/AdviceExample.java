package com.wvkia.springsource.AOP.AspectJ.advices;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class AdviceExample {

    // Before ==========

    //使用引用切点声明
    @Before("com.wvkia.springsource.AOP.AspectJ.pointcuts.SystemArchitecture.anyPublicOpearion()")
    public void doAccessCheck() {
        //do something
    }

    //或者直接写上切点表达式
    @Before("execution(* com.wvkia.springsource.AOP.AspectJ.*.*(..))")
    public void doAccessCheck2(){
        //do something
    }


    //After retuning advice
    //匹配的方法正常返回，使用@AfterReturning

    @AfterReturning("com.wvkia.springsource.AOP.AspectJ.pointcuts.SystemArchitecture.inWebLayer()")
    public void doAfterReturning(){
        //do something
    }

    //或者需要获取真实的返回值，可以使用@AfterReturning绑定返回值
    @AfterReturning(pointcut = "com.wvkia.springsource.AOP.AspectJ.pointcuts.SystemArchitecture.inWebLayer()", returning = "value")
    //注意return属性绑定的名称一定与方法的参数名一致，当一个方法返回后，返回值会被传递给这个 参数，然后在advice中就可以拿到
    public void doAfterReturningValue(Object value) {
        //
    }

    //After throwing advice
    //匹配方法执行并通过抛出异常退出时执行

    @AfterThrowing("com.wvkia.springsource.AOP.AspectJ.pointcuts.SystemArchitecture.inWebLayer()")
    public void doAfterThrowing(){
        //
    }

    //After finally advice
    //匹配的方法无论怎么样都会执行，使用@After 注解，通常用于释放资源
    @After("com.wvkia.springsource.AOP.AspectJ.pointcuts.SystemArchitecture.inWebLayer()")
    public void doAfterFinnnaly(){
        //
    }
    




}
