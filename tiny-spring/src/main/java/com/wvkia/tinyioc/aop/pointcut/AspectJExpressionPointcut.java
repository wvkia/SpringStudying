package com.wvkia.tinyioc.aop.pointcut;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 通过集成aspect来实现对切面的解析
 * @author wukai
 * @date 2019/3/17
 */
public class AspectJExpressionPointcut implements Pointcut,ClassFilter,MethodMatcher {

    private PointcutParser pointcutParser;

    private String expression;

    private PointcutExpression pointcutExpression;


    private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();
    static {
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    public AspectJExpressionPointcut(Set<PointcutPrimitive> supportedPrimitives) {
        this.pointcutParser = PointcutParser
                .getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives);
    }

    public AspectJExpressionPointcut() {
        this(DEFAULT_SUPPORTED_PRIMITIVES);
    }

    @Override
    //是否类匹配
    public boolean matches(Class targetClass) {
        checkReadyToMatch();
        //使用PointcutExpression的接口进行判断
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    @Override
    //是否方法匹配
    public boolean matches(Method method, Class targetClass) {
        checkReadyToMatch();

        //检测pointcut是否满足给定的切面
        ShadowMatch shadowMatch = pointcutExpression.matchesAdviceExecution(method);

        //总是满足
        if (shadowMatch.alwaysMatches()) {
            return true;
        } else if (shadowMatch.neverMatches()) {
            return false;
        }
        return false;
    }

    protected void checkReadyToMatch() {
        if (pointcutExpression == null) {
            pointcutExpression = buildPointcutExpression();
        }
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
    private PointcutExpression buildPointcutExpression() {
        //解析表达式
        return pointcutParser.parsePointcutExpression(expression);
    }


    @Override
    public ClassFilter getClassFilter() {
        return this;
    }
    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
