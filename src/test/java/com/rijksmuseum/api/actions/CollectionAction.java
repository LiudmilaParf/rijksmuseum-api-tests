package com.rijksmuseum.api.actions;

import com.rijksmuseum.api.models.collection.ArtObjects;
import com.rijksmuseum.api.models.collection.CollectionResponse;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.rijksmuseum.api.utilities.Route.COLLECTION;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class CollectionAction {

    public ValidatableResponse getCollectionByParam(Map<String, Object> queryParams, RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        return given(requestSpec)
                .queryParams(queryParams)
                .when()
                .get(COLLECTION)
                .then()
                .spec(responseSpec);
    }

    public List<String> getObjectNumberListByInvolvedMaker(String involvedMaker, RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        return this.getCollectionByParam(Map.of("involvedMaker", involvedMaker), requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class)
                .getArtObjects()
                .stream()
                .map(ArtObjects::getObjectNumber)
                .filter(on -> on != null && !on.isBlank())
                .toList();
    }

    public String getRandomObjectNumber(String involvedMaker, RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        List<String> objectNumbers = this.getObjectNumberListByInvolvedMaker(involvedMaker, requestSpec, responseSpec);
        int randomIndex = ThreadLocalRandom.current().nextInt(objectNumbers.size());
        return objectNumbers.get(randomIndex);
    }
}
