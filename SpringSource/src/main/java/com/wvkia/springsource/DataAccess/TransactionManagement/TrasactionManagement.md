#### Spring transaction abstraction

##### Spring事务抽象的概念在于 #事务策略#
事务策略接口定义由PlatformTransactionManager接口

PlatformTransactionManager是一个服务提供接口service provider interface，可以通过编程性然后子啊自己的项目中使用
可以像其他bean一样被实现然后由IOC管理

getTransaction(..)方法返回一个TransactionStatus对象，根据一个TransactionDefinition参数，TransactionStatus代表一个新的事务或者一i已经存在的事务
TransactionDefinition接口指明了：
+ Isolation：事务之间的隔离级别，这个可以参考事务隔离级别分类
+ Propagatoin：通常所有代码在一个事务里，但可以在代码中定义某些暂停当前事务并且重启一个新的事务
+ Timeout：事务超时时间会被回滚
+ Read-only status：一个只读事务，不修改数据

TransationStatus接口可以控制事务执行并且查询事务状态
```
public interface TransactionStatus extends SavepointManager {

	boolean isNewTransaction();

	boolean hasSavepoint();

	void setRollbackOnly();

	boolean isRollbackOnly();

	void flush();

	boolean isCompleted();

}
```

在Spring事务中,PlatformTransactionManager是关键，通常需要指定的环境：例如JDBC，Hibernate等等，也可以自定义自己的PlatformTransactionManager实现
可以通过datasource
```
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    
</>
<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="dataSource"/>
</bean>
```

或者通过Hibernate，Hrbernat需要一个sessionFactory
```
<bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
	<property name="dataSource" ref="dataSource"/>
</bean>
<bean id="txManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager">
	<property name="sessionFactory" ref="sessionFactory"/>
</bean>
```

##### Declarative transaction management声明式事务管理
    声明式事务不仅减少代码，还是非侵入式

理解Spring 声明式事务

Spring声明式事务最重要的概念是支持"AOP代理",并且Spring事务通知advice是由metadata驱动，这里的metadata（元数据）是Xml或者注解

SpringAOP和事务的metadata共同产生一个AOP代理，使用一个 TransactionInterceptor连接 PlatformTransactionManager实现一个 around method invocations环绕方法调用

![](tx.png)


##### Rolling back a declarative transaction回滚事务

Spring事务框架通常的做法是在一个事务上下文中，当代码抛出throw Exception的时候，事务会回滚。Spring会捕捉任何未处理的异常，并向上
传递，并决定是否回滚操作。

默认配置中，Spring仅仅对于运行时异常，未检查异常进行回滚，也就是RutimeException或者它的子类，Error默认也会回滚。但检查异常被抛出时不会回滚。

也可以配置异常Exception类型，包括受检查异常
也可以配置不被rollback的异常类型
```
<tx:advice id="txAdvice" transaction-manager="txManager">
    <tx:attributes>
    <tx:method name="get*" read-only="true" 
            rollback-for="NoProductInStockException
            no-rollback-for="InstrumentNotFoundException""/>
    <tx:method name="*"/>
    </tx:attributes>
</tx:advice>
```

可以通过``` <aop:advisor />` ``给不同的bean配置不同的事务advice

```< tx:advice/>```配置
+ Propagation setting 是 REQUIRED
+ Isolation隔离级别 DEFAULT，使用数据库的隔离级别
+ Transaction 事务配置 read/write
+ 任何RuntimeException都会rollback，任何Checked Exception都不会回滚

###### 使用 @Transactional 注解

