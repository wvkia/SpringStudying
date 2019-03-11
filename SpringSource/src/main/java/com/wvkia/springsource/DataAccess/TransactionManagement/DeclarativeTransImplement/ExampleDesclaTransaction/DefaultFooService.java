package com.wvkia.springsource.DataAccess.TransactionManagement.DeclarativeTransImplement.ExampleDesclaTransaction;
//接口的实现
public class DefaultFooService implements FooService {
    @Override
    public Object getFoo(String fooName) {
        throw new UnsupportedOperationException();

    }

    @Override
    public Object getFoo(String fooName, String barName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertFoo(Object foo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateFoo(Object foo) {
        throw new UnsupportedOperationException();

    }
}
