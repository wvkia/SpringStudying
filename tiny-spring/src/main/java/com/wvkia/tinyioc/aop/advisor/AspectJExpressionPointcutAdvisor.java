package com.wvkia.tinyioc.aop.advisor;

import com.wvkia.tinyioc.aop.pointcut.AspectJExpressionPointcut;
import com.wvkia.tinyioc.aop.pointcut.Pointcut;
import org.aopalliance.aop.Advice;

/**
 * @author wukai
 * @date 2019/3/17
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    //切点对象
    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
