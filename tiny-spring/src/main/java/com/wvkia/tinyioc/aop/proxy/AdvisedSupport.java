package com.wvkia.tinyioc.aop.proxy;

import com.wvkia.tinyioc.aop.pointcut.MethodMatcher;
import com.wvkia.tinyioc.aop.TargetSource;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * 代理相关的元数据
 * 封装类TargetSource，MethodInterceptro和Methodmatcher
 * @author wukai
 * @date 2019/3/14
 */
public class AdvisedSupport {

    // 要拦截的对象
    private TargetSource targetSource;

    //方法拦截器，实现AOP接口
    private MethodInterceptor methodInterceptor;

    // 方法匹配器，判断是否是需要拦截的方法
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
