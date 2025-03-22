package com.rijksmuseum.api.tests;

import com.rijksmuseum.api.actions.CollectionAction;
import com.rijksmuseum.api.models.ArtObjects;
import com.rijksmuseum.api.models.CollectionResponse;
import com.rijksmuseum.api.utilities.Route;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;

public class CollectionDetailsTests extends BaseTest {

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    CollectionAction collectionAction;
    String objectNumber;

    @BeforeMethod
    public void getObjectNumber() {
        requestSpec = getRequestSpec();
        responseSpec = getResponseSpec();
        collectionAction = new CollectionAction();
        objectNumber = collectionAction.getCollectionByParam(
                        Map.of("involvedMaker", "Rembrandt van Rijn"), requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class)
                .getArtObjects().stream().map(ArtObjects::getObjectNumber).toList().get(0);
    }

    @Test(description = "", groups = {"smoke", "collectionDetails"})
    public void getCollectionDetailsByObjectNumber() {
        ValidatableResponse response = RestAssured.given()
                .spec(getRequestSpec())
                .when()
                .get(Route.COLLECTION + "/" + objectNumber)
                .then()
                .statusCode(SC_OK);
    }
}
