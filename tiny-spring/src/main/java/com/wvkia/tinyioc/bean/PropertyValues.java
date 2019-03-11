package com.wvkia.tinyioc.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 包装一个对象所有的propertyValue
 * 不直接使用一个list的原因是可以对其做一些操作
 * @author wukai
 * @date 2019/3/11
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

    public PropertyValues() {
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        //这里可以对重复的propertyName进行判断
        this.propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
