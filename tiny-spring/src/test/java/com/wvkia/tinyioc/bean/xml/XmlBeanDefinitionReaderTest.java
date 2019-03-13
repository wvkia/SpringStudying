package com.wvkia.tinyioc.bean.xml;

import com.wvkia.tinyioc.bean.BeanDefinition;
import com.wvkia.tinyioc.bean.io.ResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author wukai
 * @date 2019/3/12
 */
public class XmlBeanDefinitionReaderTest {

    @Test
    public void loadBeanDefinitions() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("ioc.xml");

        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        Assert.assertTrue(registry.size() > 0);


        for (String name : registry.keySet()) {
            System.out.println("beanName = "+name + ", value = " + registry.get(name));
        }
    }
}