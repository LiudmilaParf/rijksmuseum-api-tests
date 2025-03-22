package com.rijksmuseum.api.configuration;

public class Configuration extends AbstractConfiguration{

    @Override
    protected String getConfigPath() {
        return "src/test/resources/general.properties";
    }

    @Override
    public void loadProperties() {

    }

    public String getBaseUrl(){
        return getProperty("base.url");
    }

    public String getCulture(){
        return getProperty("culture");
    }
}
