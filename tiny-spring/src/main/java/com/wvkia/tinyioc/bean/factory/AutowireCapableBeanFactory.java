package com.wvkia.tinyioc.bean.factory;

import com.wvkia.tinyioc.BeanReference;
import com.wvkia.tinyioc.aop.BeanFactoryAware;
import com.wvkia.tinyioc.bean.BeanDefinition;
import com.wvkia.tinyioc.bean.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 可自动装配内容的BeanFactory
 * @author wukai
 * @date 2019/3/14
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    //对bean的属性的操作
    @Override
    protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
        //如果这个bean继承了BeanFactoryAwaire的接口，就将BeanFactory注入进去
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }

        //遍历所有需要注入的bean引用
        for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValueList()) {
            Object value = propertyValue.getValue();

            //如果value引用是BeanReference，也就是引用bean，而不是其他值
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                //获取对应bean引用，referenceName对应的bean
                //getBean中如果不存在就新建，也就是将创建bean的动作放到
                //getBean的时候才做，防止出现相互引用的问题
                value = getBean(beanReference.getName());
            }


            try {
                //获取Bean需要注入对应引用的set方法
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0, 1).toUpperCase()
                                + propertyValue.getName().substring(1), value.getClass(
                        ));
                //设置操作可做
                declaredMethod.setAccessible(true);
                //反射调用set方法
                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                //如果方法不存在，就直接设置field
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }
        }
    }
}
