package com.wvkia.tinyioc.aop;

import com.wvkia.tinyioc.aop.pointcut.AspectJExpressionPointcut;
import com.wvkia.tinyioc.HelloWorldService;
import com.wvkia.tinyioc.HelloWorldServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * 使用aspectj切面
 * @author wukai
 * @date 2019/3/17
 */
public class AspectJExpressionDemo {
    @Test
    public void  testClassFilter() throws NoSuchMethodException {
        String expression = "execution(* com.wvkia.tinyioc.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);

        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldService.class);

        Assert.assertTrue(matches);
    }

    @Test
    public void  testMethodFilter() throws NoSuchMethodException {
        String expression = "execution(* com.wvkia.tinyioc.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);

        boolean matches = aspectJExpressionPointcut
                .getMethodMatcher()
                .matches(HelloWorldServiceImpl.class.getDeclaredMethod("hello"), HelloWorldServiceImpl.class);

        Assert.assertTrue(matches);
    }

}
