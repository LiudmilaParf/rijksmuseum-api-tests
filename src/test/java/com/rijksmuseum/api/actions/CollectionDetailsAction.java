package com.rijksmuseum.api.actions;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.rijksmuseum.api.utilities.Route.COLLECTION;
import static io.restassured.RestAssured.*;

public class CollectionDetailsAction {

    public ValidatableResponse getCollectionDetailsByObjectNumber(String objectNumber, RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        return given()
                .spec(requestSpec)
                .when()
                .get(COLLECTION + "/" + objectNumber)
                .then()
                .spec(responseSpec);
    }
}
