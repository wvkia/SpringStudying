package com.wvkia.tinyioc.aop.proxy;

import com.wvkia.tinyioc.aop.ReflectiveMethodInvocation;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于JDK动态代理实现的切面
 *
 * 实现AbstractAoprpxy代理接口，以及JDK动态代理的InvocationHandler代理处理器
 *
 * 有了基本的织入功能
 * 注意：实现了InvocationHandler
 * @author wukai
 * @date 2019/3/14
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {
    public JdkDynamicAopProxy(AdvisedSupport advised) {
        super(advised);
    }

    @Override
    public Object getProxy() {
        //通过jdk动态代理获取一个代理对象
        return Proxy.newProxyInstance(getClass().getClassLoader(), advised.getTargetSource().getInterfaces(), this);
    }

    /**
     * jdk动态代理调用处理器
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取方法切面包装类
        //也就是对方法对切面处理，需要做的动作
        MethodInterceptor methodInterceptor = advised.getMethodInterceptor();

        //如果存在方法检测适配类型，就检测一下对不对
        if (advised.getMethodMatcher() != null
                && advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
            return methodInterceptor.invoke(
                    new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args)
            );
        }else {

            return method.invoke(advised.getTargetSource().getTarget(), args);
        }
    }
}
