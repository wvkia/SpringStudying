Spring事务的理解

Spring事务和Spring的传播机制，本质上是对jdbc调用的封装
而我们jdbc通常执行sql是
Connection=datasource.getConnecction()
Statement = connection.createConnection()
然后statement.execute(sql)进行执行

而jdbc操作事务，实际上又是对数据库事务的调用，

而数据库事务是怎么办到的呢？

首先理解为什么要有事务，ACID原则
数据库本质是行列二维表，我们操作的都是行和列的数据，一个人操作，没有问题，两个人多个人操作的话，就会造成同步问题，就会有经典的取钱，为了维护多个人操作数据库仍能保持正确性，就必须满足ACID原则。
而且既然是同步问题，就涉及锁的应用，这是另一个问题了，暂且不表。

数据库为了满足ACID，就必须有事务，而数据库的事务是通过，通过命令是这样的
```language
begin/start transaction;	//这样就开启了一个事务
insert sql;	//sql语句的操作
//其实这时候你看到执行成功，其实还没有被提交到数据库，没有被写到磁盘上，只是保存到一块缓冲区
commit;	//提交事务，这个时候事务完成，把数据全部写入到磁盘

rollback; //回滚命令，会结束当前事务的同时，撤销未提交的所有操作，也就是把缓冲区清掉，不写入磁盘，数据库没有任何操作的数据记录。
```
而事务的隔离级别又是什么呢？
如果采用很严格的事务，那么锁的应用会很多，很影响效率，有时候我们不需要满足这个严格的事务，所以有四个隔离级别用来设定。READ UNCOMMITTED、READ COMMITTED、REPEATABLE READ和SERIALIZABLE。

数据库的事务说完了，然后说jdbc怎么使用数据库的事务？
jdbc本质上是一个规范，驱动就是实现这些规范的代码，驱动代码通过建立一个到数据库ip的socket连接，然后调用数据库的命令来实现事务。
具体代码实现：
```language
Connecton = datasource.getConnection();	
connection.setAutoCommint(false);//connection就是一个连接，默认是启用自动提交的，如果我们要自己操作事务，需要把这个参数设置为false
//然后通过statement执行语句
//最后根据语句执行情况决定是commit提交还是rollback回滚
connection.rollback或者connection.commit()
//最后需要把自动提交参数恢复，因为我们往往都是通过连接池拿connection，
connection.setAutoCommit(true)
```

然后是Spring的事务，Spring的事务本质就是对jdbc的封装，简便了代码，而事务的传播又是怎么回事呢？我们通过jdbc原生如何实现？
Spring的事务传播机制有PROPAGATION_REQUIRED ，PROPAGATION_SUPPORTS ，PROPAGATION_REQUIRES_NEW 等等。
我们知道PROPAGATION_REQUIRES_NEW 的意思是新建一个事务，同时挂起当前事务，怎么做到的？
一个serviceA的方法A有一个事务，serviceB有个B方法也有个事务，如果serviceA的A调用了serviceB的b，事务怎么操作？
本质上就是如果有了一个事务，也就是jdbc中拿到了一个connection，同时设置了自动提交autoCommit(false)，这时候如果调用另一个拥有事务的方法，会重新在被调用的方法上new一个connection然后用这个connection执行事务操作，执行完成后，这个事务结束，然后回到刚才上面的那个connection，继续它的事务。

然后用PROPAGATION_REQUIRES做个举例，如果A有事务，调用B，B也有事务，B就不会重新新建connection执行，而使用A的conneciton，这时，如果A，B任意发生了异常，抛出了Exception，A和B的事务都会被回滚，因为用的是一个connection