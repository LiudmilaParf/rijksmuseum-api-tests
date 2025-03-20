package com.rijksmuseum.api.tests;

import com.rijksmuseum.api.configuration.Configuration;
import com.rijksmuseum.api.utilities.SecretsProvider;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import io.qameta.allure.restassured.AllureRestAssured;

public class BaseTest {
    protected static Configuration config;
    protected static SecretsProvider secrets;
    protected static String baseUrl;
    protected static String apiKey;

    @BeforeClass
    public void setup() {
        config = new Configuration();
        secrets = new SecretsProvider();
        baseUrl = config.getBaseUrl();
        apiKey = secrets.getApiKey();

        RestAssured.baseURI = baseUrl;

        // Configure RestAssured to handle JSON responses correctly
        RestAssured.config = RestAssuredConfig.config()
                .objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                        (type, s) -> {
                            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
                            objectMapper.findAndRegisterModules(); // Auto-detects Jackson modules
                            return objectMapper;
                        }
                ));
    }

    public RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .addQueryParam("key", apiKey) // Automatically adds API key
                .addQueryParam("format", "json") // Ensures JSON format in API responses
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured()) // Attach request/response to Allure
                .log(LogDetail.ALL)
                .build();
    }

    public ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
