package com.wvkia.springsource.Container.AddCapabilityOfApplicationContext_15.CustomEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * 自定义事件发布
 */
@Component
public class CustomPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void doSomething(String msg){
        System.out.println("Publishing custom event. ");
        CustomEvent event = new CustomEvent(this,msg);
        applicationEventPublisher.publishEvent(event);
        return;
    }
}
