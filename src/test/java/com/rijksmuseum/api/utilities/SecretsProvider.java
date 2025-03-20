package com.rijksmuseum.api.utilities;

import com.rijksmuseum.api.configuration.AbstractConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SecretsProvider extends AbstractConfiguration {
    @Override
    protected String getConfigPath() {
        return "src/test/resources/secrets.properties";
    }

    public String getApiKey() {
        // Try to get API key from secrets.properties
        String apiKey = getProperty("api.key");

        // If not found, get API key from environment variable (GitHub Actions)
        if (apiKey == null || apiKey.isEmpty()) {
            apiKey = System.getenv("API_KEY"); // Read from GitHub Actions environment
        }

        // If still missing, throw an error
        if (apiKey == null || apiKey.isEmpty()) {
            throw new RuntimeException("API key is missing in secrets.properties file!");
        }
        return apiKey;
    }

    @Override
    public void loadProperties() {
        File file = new File(getConfigPath());

        if (!file.exists()) {
            System.err.println("Secrets.properties file not found.");
            return; // Prevent crashing but warn user
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load secrets.properties file.", e);
        }
    }
}
