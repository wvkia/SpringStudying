
### bean目录

bean目录下分为三个子目录和部分类

首先对类进行解释

**BeanDefinition类**

Bean的元数据，spring的bean从来不是单独一个实现，然后保存起来的，还需要一些其他的信息来完成额外的操作。
BeanDefinition就是用来封装Bean的定义类

```java
public class BeanDefinition {
    //实际真正的bean
    private Object bean;

    //bean对应的class
    private Class beanClass;

    private String beanClassName;

    //bean所有的属性封装的对象
    private PropertyValues propertyValues = new PropertyValues();
}

```

**PropertyValue**

用于保存bean的属性注入
```java
public class PropertyValue {

    //bean需要注入的引用bena名称
    private final String name;

    //bean中需要注入的引用bean
    private final Object value;
}

```

**PropertyValues**

包装一个对象所有的PropertyValue，一个bean不可能只有一个属性注入，所以
采用一个类单独封装一个propertyValue list。可以再次基础上对其做一些操作


**BeanDefinitionReader接口**
从配置中读取BeanDefinition的接口

AbstractBeanDefinitionReader缺省适配模式，继承BeanDefinitionReader接口，同时包装部分公用接口

xml目录下的xmlBeanDefinitionReader是作为AbstractBeanDefinitionReader的一个xml实现，实现从xml中读出
bean的定义

**BeanPostProcessor接口**
bean初始化前后可以做的动作



factory下有BeanFactory接口，是创建bean对工厂模式。而AbstractBeanFactory则是继承了BeanFactory接口，
实现了缺省适配模式，在继承BeanFactory接口对基础上，添加公共默认实现方法。

io目录下Resource接口是为了获取资源数据流，UrlResource是对Resource接口的URL实现。
ResourceLoader作为工厂模式，返回一个Resource的接口实现。


context目录
ApplicationContext继承BeanFactory接口，在BeanFactory的基础上还可以封装其他的操作
AbstractApplicationContext作为ApplicationContext的缺省适配模式，实现对context上下文的公共方法封装


### AOP

AOP分为配置（PointCut，Advice）和织入（Weave）两部分工作，还要将AOP整合到整个容器的生命周期

织入(weave)，Spring Aop的织入点是AopProxy，它包含一个方法``Object getProxy()``来获取代理后的对象。

org.aop的MethodInterceptor和MethodInvocation对应的是AOP联盟的标准，对应AOP的基本角色：Advice和Joinpoint。
Advice定义了在切点指定的逻辑，而Joinpoint定义了切点。

被代理对象使用``TargetSource``封装，而``AdvisedSupport``就是保存TargetSource和MethodInterceptor的元数据对象。
```java

public void test(){
    
    //helloWorldService without AOP
    
}

```


如何确定一个切点表达式对什么类以及什么方法进行AOP？
我们需要使用Pointcut的定义来确定。Pointcut包含两个角色：ClassFilter和MethodMatcher，分别对类和方法进行匹配。

AOP使用pointcut进行切面定位，使用weave技术将AOP整合到容器，如何结合到Spring中？
Spring使用Bean的BeanPostProcessor，也就是后置处理

BeanPostProcessor是BeanFactory提供的，在Bean初始化过程中进行扩展的接口。只有Bean实现类BeanPostProcessor接口，SPring在bean初始化时就会调用这个接口，实现对BeanFactory
核心无侵入的扩展。

我们的AOP是怎么实现的呢？在AOP的xml配置中，有这么一句话 ``<aop:aspectj-autoproxy>``，相当于
```xml
<bean id="autoProxyCreator" class="org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator"></bean>
```

``AspectJAwareAdvisorAutoProxyCreator``就是AspectJ方式实现织入的核心。其实是一个BeanPostProcessor，会扫描所有的Pointcut，并对bean进行织入

我们自己实现了AspectJAwareAdvisorAutoProxyCreator,然后直接使用bean的方式

```xml

<bean id ='autoProxyCreator' class="com.wvkia.tinyioc.aop.AspectJAwareAdvisorAutoProxyCreator"></bean>

<!--实现一个切面定义-->
<bean id="timeInterceptor" class="com.wvkia.tinyioc.aop.TimerInterceptor"></bean>
<!--将一个切面advice注入，并结合expression表达式-->

<bean id="aspectjAspect" class="com.wvkia.tinyioc.aop.advisor.AspectJExpressionPointcutAdvisor"> 
    <property name="advice" ref="timeInterceptor"></property>
    <property name="expression" value="execution(* com.wvkia.tinyioc.*.*(..))"></property>
</bean>
```
