package com.wvkia.springsource.Container.Dependencies_4.dependencies;

import java.beans.ConstructorProperties;

public class ExampleBean2 {
    private int year;
    private String name;

    //通过注解设置值
    @ConstructorProperties({"123","name"})
    public ExampleBean2(int year, String name) {
        this.year = year;
        this.name = name;
    }
}
