package com.wvkia.springsource.Container.AnnotationBasedConfiguration_9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SimpleCataLogManager {

    @Autowired
    private SimpleCatalog simpleCatalog;

    @Autowired
    private Map<String, SimpleCatalog> map;

    public Map<String, SimpleCatalog> getMap() {
        return map;
    }

    public void setMap(Map<String, SimpleCatalog> map) {
        this.map = map;
    }

    public SimpleCatalog getSimpleCatalog() {
        return simpleCatalog;
    }

    public void setSimpleCatalog(SimpleCatalog simpleCatalog) {
        this.simpleCatalog = simpleCatalog;
    }
}
