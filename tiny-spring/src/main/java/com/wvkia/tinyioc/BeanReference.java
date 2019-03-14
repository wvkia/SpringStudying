package com.wvkia.tinyioc;

/**
 * @author wukai
 * @date 2019/3/11
 * 用于bean的依赖操作，注入其他bean的引用
 * 用来表示属性是对其他对象对引用的封装
 */
public class BeanReference {
    //引用bean的name
    private String name;

    //引用的bean
    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
