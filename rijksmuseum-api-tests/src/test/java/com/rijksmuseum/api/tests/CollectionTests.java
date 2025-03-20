package com.rijksmuseum.api.tests;

import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@Story("RM-1 RM API: GetCollections")
public class CollectionTests {

    @Test(description = "GET /collection", groups = {"smoke", "collections"})
    public void getCollection() {
        Response response = given()
                .when()
                .get("https://www.rijksmuseum.nl/api/nl/collection?key=0fiuZFh4&involvedMaker=Rembrandt+van+Rijn");
        assertEquals(response.statusCode(), 200, "Expected status code 200");
    }
}
