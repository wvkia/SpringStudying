package com.wvkia.springsource.Container.AddCapabilityOfApplicationContext_15.CustomEvent;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 */
public class CustomEvent extends ApplicationEvent {
    private String message;

    public CustomEvent(Object source,String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
