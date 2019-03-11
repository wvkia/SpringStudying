package com.wvkia.springsource.Container.AddCapabilityOfApplicationContext_15.CustomEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventReceiver implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent customEvent) {
        System.out.println("Received spring custom event - "+customEvent.getMessage());
    }
}
