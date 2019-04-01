package com.wvkia.springCode.extra;

import com.wvkia.springCode.service.HelloService;
import com.wvkia.springCode.service.impl.HelloServiceImpl;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author wukai
 * @date 2019/4/1
 */
public class MyFactoryBean implements FactoryBean<FactoryBeanModel> {
    public FactoryBeanModel getObject() throws Exception {
        return new FactoryBeanModel();
    }

    public Class<?> getObjectType() {
        return FactoryBeanModel.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
