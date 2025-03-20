package com.rijksmuseum.api.tests;

import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

@Story("RM-1 RM API: GetCollections")
public class CollectionTests extends BaseTest {

    @Test(description = "GET /collection", groups = {"smoke", "collections"})
    public void getCollection() {

        ValidatableResponse response1 = given()
                .spec(getRequestSpec())
                .queryParam("involvedMaker", "Rembrandt van Rijn")
                .when()
                .get("/collection")
                .then()
                .log().body()
                .assertThat()
                .statusCode(SC_OK);
    }
}
