#### Spring bean singleton作用域的bean注入prototype原型bean
基本知识点：
当spring容器不同作用域的bean相互依赖，可能会有问题。例如一个作用域是singleton的bean A，依赖于一个作用域prototype的bean B。由于A是单例的，只被初始化一次，它的依赖管理也被初始化一次，这样的话A也就被初始化一次，所以不能达到prototype的效果。


当前项目遇到问题就是，有一个bean需要每个线程都重新new一个新的对象，但spring管理的bean默认是singleton模式，所以需要改成prototype ，然后就遇到两个问题：
1、如果是直接加上prototype是不起作用的
因为单例的bean只被初始化一次，所以需要使用代理或者使用 ApplicationContextAware接口设置ApplicationContext，然后通过context.getbean获取到，又或者通过方法注入 lookup设置

2、如果使用动态代理的方式，应该是
```language
在bean上加上 @Scope(value=ConfigurableBeanFactory.Prototype，proxyMode=ScopedProxyMode.Target_class)
```

但是我们项目需要在原型prototype bean设置属性，而且是通过bean.value的形式，采用动态代理没有办法获取到属性，获取的都是null，这样的话就只能通过ApplicationContextAware接口了

我的方法是通过工厂模式，首先一个单例的service实现ApplicationContextAware接口，设置好了ApplicationContext后，开放一个createInstance方法，根据传入的类型，拿到原型prototype的name，根据context.getBean(name,Class.class)拿到bean，原型prototype每次getBean都是全新的一个对象

由上的ApplicationContextAware接口还可以用来获取一个接口多个service，通过context.getBeanOfType(Interface.class)拿到所有接口的实现，然后通过 context.findAnnotationOnBean()拿到bean的注解，从而获取到name，再根据工厂方法参数拿到不同的实例对象

https://waylau.com/spring-singleton-beans-with-prototype-bean-dependencies/
https://zhuanlan.zhihu.com/p/27971569
http://free0007.iteye.com/blog/2064678
