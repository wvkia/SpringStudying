package com.wvkia.tinyioc.bean;

/**
 * Bean 在IOC容器的定义，IOC容器可以根据这个定义来生成实例
 *
 * 以BeanDefinition发散的类，都是用于解决 Bean 的具体定义问题，包括Bean的名字，类型，属性赋予哪些值或者引用
 * @author wukai
 * @date 2019/3/11
 */
public class BeanDefinition {
    //实际真正的bean
    private Object bean;

    //bean对应的class
    private Class beanClass;

    /**
     * bean的名字
     */
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
