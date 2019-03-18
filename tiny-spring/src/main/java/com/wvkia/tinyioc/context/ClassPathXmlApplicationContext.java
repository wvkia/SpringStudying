package com.wvkia.tinyioc.context;

import com.wvkia.tinyioc.bean.BeanDefinition;
import com.wvkia.tinyioc.bean.factory.AbstractBeanFactory;
import com.wvkia.tinyioc.bean.factory.AutowireCapableBeanFactory;
import com.wvkia.tinyioc.bean.io.ResourceLoader;
import com.wvkia.tinyioc.bean.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author wukai
 * @date 2019/3/17
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation,AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    //加载bean的定义
    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        //xml beandefintion读取xml bean定义
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        //注册
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }
}
