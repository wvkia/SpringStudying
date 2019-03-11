package com.wvkia.springsource.Container.ClasspathScanning_10.NameGenerator;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

public class MyNameGenerator implements BeanNameGenerator {
    @Override
    public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {
        String generatedBeanName = beanDefinition.getBeanClassName();
        if (generatedBeanName == null) {
            if (beanDefinition.getParentName() != null) {
                generatedBeanName = beanDefinition.getParentName() + "$child";
            } else if (beanDefinition.getFactoryBeanName() != null) {
                generatedBeanName = beanDefinition.getFactoryBeanName() + "$created";
            }
        }
        return "myname_" + generatedBeanName;
    }
}
