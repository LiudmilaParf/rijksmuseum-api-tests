package com.rijksmuseum.api.tests;

import com.rijksmuseum.api.models.CollectionResponse;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Story("RM-1 RM API: GetCollections")
public class CollectionTests extends BaseTest {

    @Test(description = "GET /collection with required key only", groups = {"smoke", "collections"})
    public void getCollectionWithDefaultParam() {
        CollectionResponse response = given()
                .spec(getRequestSpec())
                .when()
                .get("/collection")
                .then()
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertFalse(response.getArtObjects().isEmpty());
    }

    @Test(description = "GET /collection by involvedMaker", groups = {"smoke", "collections"})
    public void getCollectionByInvolvedMaker() {
        CollectionResponse response = given()
                .spec(getRequestSpec())
                .queryParam("involvedMaker", "Rembrandt van Rijn")
                .when()
                .get("/collection")
                .then()
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertEquals(response.getArtObjects().get(0).getPrincipalOrFirstMaker(), "Rembrandt van Rijn");
    }
}
