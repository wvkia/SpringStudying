package com.wvkia.tinyioc.aop;

import com.wvkia.tinyioc.aop.advisor.AspectJExpressionPointcutAdvisor;
import com.wvkia.tinyioc.aop.proxy.ProxyFactory;
import com.wvkia.tinyioc.bean.BeanPostProcessor;
import com.wvkia.tinyioc.bean.factory.AbstractBeanFactory;
import com.wvkia.tinyioc.bean.factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * 切面产生类
 * @author wukai
 * @date 2019/3/17
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor,BeanFactoryAware {

    private AbstractBeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    //后置处理
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        //如果是AspectJExpressionPointcutAdvisor
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        //如果是MethodIntercptor
        if (bean instanceof MethodInterceptor) {
            return bean;
        }

        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);

        //遍历所有的 表达式Advisor接口实现
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            //如果类匹配
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {

                //构建一个代理工厂
                ProxyFactory advisedSupport = new ProxyFactory();
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);

                return advisedSupport.getProxy();
            }
        }
        return bean;
    }
}
