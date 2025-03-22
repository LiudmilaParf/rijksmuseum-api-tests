package com.rijksmuseum.api.actions;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static com.rijksmuseum.api.utilities.Route.COLLECTION;
import static io.restassured.RestAssured.given;

public class CollectionAction {

    public ValidatableResponse getCollectionByParam(Map<String, Object> queryParams, RequestSpecification requestSpec, ResponseSpecification responseSpec){
        return given(requestSpec)
                .queryParams(queryParams)
                .when()
                .get(COLLECTION)
                .then()
                .spec(responseSpec);
    }
}
