package com.epam.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    Properties properties;

    public PropertyManager() {
        this.properties = new Properties();
    }

    public void load(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }


}
