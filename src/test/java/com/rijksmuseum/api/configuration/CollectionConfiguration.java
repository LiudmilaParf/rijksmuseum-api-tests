package com.rijksmuseum.api.configuration;

public class CollectionConfiguration extends AbstractConfiguration{

    @Override
    protected String getConfigPath() {
        return "src/test/resources/collection.properties";
    }

    public String getInvolvedMaker() {
        return getProperty("involvedMaker");
    }

    public String getType() {
        return getProperty("type");
    }

    public String getMaterial() {
        return getProperty("material");
    }

    public String getTechnique() {
        return getProperty("technique");
    }

    public String getDatingPeriod() {
        return getProperty("f.dating.period");
    }

    public String getNormalized32ColorsHex() {
        return getProperty("f.normalized32Colors.hex");
    }
}
