package com.rijksmuseum.api.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CollectionTests {

    @Test
    public void getCollection() {
        Response response = given()
                .when()
                .get("https://www.rijksmuseum.nl/api/nl/collection?key=0fiuZFh4&involvedMaker=Rembrandt+van+Rijn");
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
    }
}
