#### Overview of Spring Framework

##### Introduction of Spring

###### 1. Dependency Injection and Inversion of Control

###### 2. Modules

![](spring-overview.png)

**CoreContainer**

CoreContainer包括 spring-core,spring-beans,spring-context,spring-context,support和spring-expression模块

spring-core和spring-bean是框架的基础模块，提供IOC和DI特性。这里的基础概念是BeanFactory，提供对Factory模式的经典实现来消除对程序单例模式的需要，允许从逻辑中分离依赖关系和配置

+ Core 包含Spring框架的基本的核心工具类
+ Beans是所有应用都要用到的，包括访问配置文件、创建和管理bean以及进行DI和IOC操作的相关的所有类
+ Context模块构建在Core和Beans模块上，提供类似JNDI注册的对象访问方式。Context继承了Bean的特性，并提供大量扩展，添加国际化、事件传播、资源加载等。ApplicationContext接口是Context模块的关键
+ Expression提供强大的表达式语句

**AOP and Instrumentation**

spring-aop模块提供面向切面编程的实现，可以让你定义例如方法拦截器和切点，从而将逻辑代码分开，降低耦合性，利用source-level的元数据功能，还可以将各种行为信息合并到代码中

单独的 spring-aspets 模块提供了AspectJ的整合

spring-instrument模块提供了类和类加载器的支持并可以应用到具体的应用服务上。spring-instrument-tomcat模块包含spring对tomcat的代理

**Data Access**

包括 JDBC,ORM,OXM,JMS and Transaction
spring-jdbc 提供jdbc抽象层，消除冗余的jdbc代码和数据库厂商错误代码
spring-tx 提供 编程式 和 声明式 两种事务管理
spring-orm 提供对象关系映射的抽象，可以继承 OR-mapping框架到自己的项目中

**Web**

包括spring-web,spring-webmvc,spring-websocket,spring-webmnc-portlet

spring-web模块提供基础的web-oriented整合，例如文件上传、使用servlet listeners和web相关上下文初始化IOC容器。

spring-mvc模块提供 spring的MVC模式和RESTFUL的web服务。

###### 3. Usage scenarios使用场景
