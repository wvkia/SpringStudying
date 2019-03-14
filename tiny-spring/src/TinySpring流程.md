
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

