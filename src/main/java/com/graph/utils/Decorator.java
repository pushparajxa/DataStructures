package com.graph.utils;

import java.util.HashMap;

public  class Decorator {
    //Decorator Pattern. Decorating the objects with properties. You can remove and add properties.
    private HashMap<String,Object> properties = new HashMap<>();

    public boolean isPropertyDefined(String property) {
        return properties.containsKey(property);
    }

    public Object getProperty(String property) {
        return properties.get(property);
    }

    public void updateProperty(String property,Object newValue) {
        properties.put(property,newValue);
    }

    public void clearProperties() {
        properties.clear();
    }
}
