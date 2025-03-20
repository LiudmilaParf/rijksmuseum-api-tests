package com.rijksmuseum.api.configuration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractConfiguration {
    protected Properties properties = new Properties();

    public AbstractConfiguration() {
        String configPath = getConfigPath();
        try (InputStream inputStream = new FileInputStream(configPath)) {
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Unable to read config file: " + configPath, e);
        }
    }

    protected abstract String getConfigPath();

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public abstract void loadProperties();
}
