package com.rijksmuseum.api.tests;

import com.rijksmuseum.api.actions.CollectionAction;
import com.rijksmuseum.api.configuration.CollectionConfiguration;
import com.rijksmuseum.api.configuration.CollectionDataProvider;
import com.rijksmuseum.api.models.collection.ArtObjects;
import com.rijksmuseum.api.models.collection.CollectionResponse;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static com.rijksmuseum.api.utilities.AssertMessages.NON_EMPTY_RESPONSE_EXPECTED;
import static com.rijksmuseum.api.utilities.AssertMessages.PARAMETER_VALUE_MISMATCH;
import static com.rijksmuseum.api.utilities.Route.COLLECTION;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@Story("RM-1 RM API: GetCollections")
public class CollectionTests extends BaseTest {
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    CollectionAction collectionAction;
    CollectionConfiguration collectionConfig;

    @BeforeMethod(groups = {"smoke", "collections"})
    public void init() {
        requestSpec = getRequestSpec();
        responseSpec = getResponseSpec();
        collectionAction = new CollectionAction();
        collectionConfig = new CollectionConfiguration();
    }

    @Test(description = "GET /collection with required key only", groups = {"smoke", "collections"})
    public void getCollectionWithDefaultParam() {
        CollectionResponse response = given()
                .spec(getRequestSpec())
                .when()
                .get(COLLECTION)
                .then()
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertThat("Expected non-empty response when only required parameter 'key' is provided, but got an empty result.",
                response.getArtObjects(), not(empty()));
    }

    @Issue("4")
    @Test(description = "GET /collection by involvedMaker", groups = {"collections"},
            dataProvider = "artists",
            dataProviderClass = CollectionDataProvider.class)
    public void getCollectionByInvolvedMaker(String artist) {
        CollectionResponse response = collectionAction.getCollectionByParam(
                        Map.of("involvedMaker", artist), requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertThat(NON_EMPTY_RESPONSE_EXPECTED, response.getArtObjects(), not(empty()));
        assertThat(PARAMETER_VALUE_MISMATCH, response.getArtObjects()
                        .stream()
                        .map(ArtObjects::getPrincipalOrFirstMaker)
                        .toList(),
                everyItem(equalTo(artist)));
    }

    @Test(description = "GET /collection by material", groups = {"collections"},
            dataProvider = "material",
            dataProviderClass = CollectionDataProvider.class)
    public void getCollectionByMaterial(String material) {
        CollectionResponse response = collectionAction.getCollectionByParam(
                        Map.of("material", material),
                        requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertThat(NON_EMPTY_RESPONSE_EXPECTED, response.getArtObjects(), not(empty()));
    }

    @Test(description = "GET /collection by object type", groups = {"collections"},
            dataProvider = "objectTypes",
            dataProviderClass = CollectionDataProvider.class)
    public void getCollectionByObjectType(String type) {
        CollectionResponse response = collectionAction.getCollectionByParam(
                        Map.of("type", type),
                        requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertThat(NON_EMPTY_RESPONSE_EXPECTED, response.getArtObjects(), not(empty()));
    }

    @Test(description = "GET /collection by technique", groups = {"collections"},
            dataProvider = "techniques",
            dataProviderClass = CollectionDataProvider.class)
    public void getCollectionByTechnique(String technique) {
        CollectionResponse response = collectionAction.getCollectionByParam(
                        Map.of("technique", technique),
                        requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertThat(NON_EMPTY_RESPONSE_EXPECTED, response.getArtObjects(), not(empty()));
    }

    @Test(description = "GET /collection by dating period", groups = {"collections"},
            dataProvider = "periods",
            dataProviderClass = CollectionDataProvider.class)
    public void getCollectionByPeriod(String period) {
        CollectionResponse response = collectionAction.getCollectionByParam(
                        Map.of("f.dating.period", period),
                        requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertThat(NON_EMPTY_RESPONSE_EXPECTED, response.getArtObjects(), not(empty()));
    }

    @Test(description = "GET /collection by multiple materials", groups = {"collections"})
    public void getCollectionByMultipleMaterials() {
        CollectionResponse response = collectionAction.getCollectionByParam(
                        Map.of("material", "canvas,karton"),
                        requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertThat(NON_EMPTY_RESPONSE_EXPECTED, response.getArtObjects(), not(empty()));
    }

    @Test(description = "GET /collection by more than one parameter", groups = {"collections"})
    public void getCollectionByTwoParams() {
        CollectionResponse response = collectionAction.getCollectionByParam(
                        Map.of("material", collectionConfig.getMaterial(),
                                "involvedMaker", collectionConfig.getInvolvedMaker()),
                        requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertThat(NON_EMPTY_RESPONSE_EXPECTED, response.getArtObjects(), not(empty()));
        assertThat(response.getArtObjects()
                        .stream()
                        .map(ArtObjects::getPrincipalOrFirstMaker)
                        .toList(),
                everyItem(equalTo(collectionConfig.getInvolvedMaker())));
    }

    @Test(description = "GET /collection with invalid API key", groups = {"negative", "collections"})
    public void getCollectionWithInvalidApiKey() {
        Response response = given()
                .spec(requestSpec)
                .queryParam("key", "invalid_key")
                .when()
                .get(COLLECTION);

        assertThat("Expected unauthorized or error status for invalid API key",
                response.statusCode(), anyOf(is(SC_UNAUTHORIZED), is(SC_FORBIDDEN)));
    }

    @Test(description = "GET /collection with non-existent material", groups = {"negative", "collections"})
    public void getCollectionWithNonexistentMaterial() {
        CollectionResponse response = collectionAction.getCollectionByParam(
                        Map.of("material", "nonexistentMaterialXyz"),
                        requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertThat("Expected empty result for unknown material", response.getArtObjects(), empty());
    }

    @Test(description = "GET /collection - Verify that page * pageSize cannot exceed 10,000", groups = {"negative", "collections"})
    public void getCollectionWithExceededPageSize() {
        String p = "101";
        String ps = "100";

        Response response = given()
                .spec(getRequestSpec())
                .queryParam("p", p)
                .queryParam("ps", ps)
                .when()
                .get(COLLECTION);

        int statusCode = response.statusCode();

        assertThat("Expected an error response (4xx or 5xx), but received status code: " + statusCode, statusCode >= 400);
    }
}
