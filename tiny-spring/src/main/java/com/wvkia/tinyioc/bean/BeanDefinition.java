package com.wvkia.tinyioc.bean;

/**
 * bean的元数据
 * spring的bean从来都不是直接一个object存在map中，需要一些其他的信息
 * 这个就是用来封装bean的类
 * @author wukai
 * @date 2019/3/11
 */
public class BeanDefinition {
    //实际真正的bean
    private Object bean;

    //bean对应的class
    private Class beanClass;

    private String beanClassName;

    //bean所有的属性封装的对象
    private PropertyValues propertyValues = new PropertyValues();

    public BeanDefinition() {
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {

        this.beanClassName = beanClassName;
        try {
            //对beanclass赋值
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
