package com.wvkia.tinyioc.bean;

/**
 * 用于bean的属性注入
 * @author wukai
 * @date 2019/3/11
 */
public class PropertyValue {

    //bean需要注入的引用bena名称
    private final String name;

    //bean中需要注入的引用bean
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
