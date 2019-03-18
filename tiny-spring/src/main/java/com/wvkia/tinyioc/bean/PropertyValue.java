package com.wvkia.tinyioc.bean;

/**
 * 用于bean的属性注入
 * @author wukai
 * @date 2019/3/11
 */
public class PropertyValue {

    //bean需要注入的引用bena名称
    private final String name;

    /**
     * Spring xml中property中，键是key，值是value或者ref，对于value只要直接注入属性就可以，对于ref需要解析对应的bean
     */
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
