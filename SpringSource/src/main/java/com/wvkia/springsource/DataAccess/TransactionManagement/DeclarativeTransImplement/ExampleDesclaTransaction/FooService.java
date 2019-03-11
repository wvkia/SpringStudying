package com.wvkia.springsource.DataAccess.TransactionManagement.DeclarativeTransImplement.ExampleDesclaTransaction;

//使其产生transactional事务
//
public interface FooService {
    Object getFoo(String fooName);

    Object getFoo(String fooName, String barName);

    void insertFoo(Object foo);

    void updateFoo(Object foo);
}
