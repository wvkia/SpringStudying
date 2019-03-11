package com.wvkia.springsource.DataAccess.TransactionManagement.DeclarativeTransImplement.ExampleDesclaTransaction;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;

public final class DeclaTransApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("trasactionContext/trasactionContext.xml");
//        ApplicationContext context = new ClassPathXmlApplicationContext("dependencies/trasactionContext.xml");
//        BasicDataSource bds = (BasicDataSource) context.getBean("datasource");
//        try {
//            Connection connection = bds.getConnection();
//            PreparedStatement statement = connection.prepareStatement("select * from mc_source");
//            statement.execute();
//            ResultSet set = statement.getResultSet();
//            while (set.next()) {
//                System.out.println(set.getString(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//
//        }
        FooService fooService = (FooService) context.getBean("fooService");
        fooService.insertFoo(new Object());

    }
}
