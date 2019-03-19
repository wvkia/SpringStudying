package com.wvkia.tinyioc.aop;

import com.wvkia.tinyioc.aop.advisor.AspectJExpressionPointcutAdvisor;
import com.wvkia.tinyioc.aop.proxy.ProxyFactory;
import com.wvkia.tinyioc.bean.BeanPostProcessor;
import com.wvkia.tinyioc.bean.factory.AbstractBeanFactory;
import com.wvkia.tinyioc.bean.factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * 实现BeanFactoryAware接口：这个接口提供了对BeanFactory对感知，这样，尽管它是一个容器对Bean，却可以获取到容器的引用，进而操作容器的所有的bean，获取容器
 * 中所有的切点对象，决定对哪些对象的哪些方法进行代理。解决了 为哪些对象 提供AOP的植入的问题
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

    /**
     * bean实例化之后进行初始化操作，会经过这个方法满足条件则生成相应的代理类并返回
     * @param bean  实际的bean
     * @param beanName bean的name
     * @return
     * @throws Exception
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        //如果是切点通知器，直接返回
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        //如果是 方法拦截器，则直接返回
        if (bean instanceof MethodInterceptor) {
            return bean;
        }

        //通过getBeansForType方法加载BeanFactory 中所有的 PointcutAdvisor(保证了 PointcutAdvisor 的实例化顺序优于普通的bean)
        // AspectJ方式实现织入，对所有的Pointcut，并对bean进行织入
        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);

        //遍历所有的 表达式Advisor接口实现
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            //如果匹配拦截的类
            //使用matchs匹配器，判断当前对象是不是需要拦截的类的对象
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {

                ProxyFactory advisedSupport = new ProxyFactory();

                //设置切点的方法拦截器
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                //设置切点的方法匹配器
                //利用AspectJ表达式进行方法匹配
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

                //要拦截的类，生成一个TargetSource（要拦截的对象和类型）（被代理对象）
                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);

                //生成代理对象
                return advisedSupport.getProxy();
            }
        }
        return bean;
    }
}
