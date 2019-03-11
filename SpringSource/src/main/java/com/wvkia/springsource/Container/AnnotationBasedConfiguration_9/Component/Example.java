package com.wvkia.springsource.Container.AnnotationBasedConfiguration_9.Component;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Example {

    @Resource
    private Compo component;

    public Compo getComponent() {
        return component;
    }

    public void setComponent(Compo component) {
        this.component = component;
    }
}
