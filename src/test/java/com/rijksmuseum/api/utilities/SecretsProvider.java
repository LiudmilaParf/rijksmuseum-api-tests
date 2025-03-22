package com.rijksmuseum.api.utilities;

import com.rijksmuseum.api.configuration.AbstractConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SecretsProvider extends AbstractConfiguration {

    private static final Logger logger = LogManager.getLogger(SecretsProvider.class);

    @Override
    protected String getConfigPath() {
        return "src/test/resources/secrets.properties";
    }

    public String getApiKey() {

        String apiKey = getProperty("api.key");

        if (apiKey != null && !apiKey.isEmpty()) {
            logger.info("API Key successfully loaded from secrets.properties.");
            return apiKey;
        }

        apiKey = System.getenv("API_KEY");

        if (apiKey != null && !apiKey.isEmpty()) {
            logger.info("API Key successfully loaded from environment variable.");
            return apiKey;
        }

        throw new RuntimeException("API key is missing! Provide it in secrets.properties or as an environment variable (`API_KEY`).");
    }

    @Override
    public void loadProperties() {
        File file = new File(getConfigPath());

        if (!file.exists()) {
            logger.warn("Secrets.properties file not found.");
            return; // Prevent crashing but warn
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
            logger.info("Loaded properties from {}", getConfigPath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load secrets.properties file.", e);
        }
    }
}
