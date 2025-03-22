package com.rijksmuseum.api.tests;

import com.rijksmuseum.api.actions.CollectionAction;
import com.rijksmuseum.api.configuration.CollectionDataProvider;
import com.rijksmuseum.api.models.ArtObjects;
import com.rijksmuseum.api.models.CollectionResponse;
import io.qameta.allure.Story;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static com.rijksmuseum.api.utilities.Route.COLLECTION;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertFalse;

@Story("RM-1 RM API: GetCollections")
public class CollectionTests extends BaseTest {
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    CollectionAction collectionAction;

    @BeforeMethod(groups = {"smoke", "collections"})
    public void init() {
        requestSpec = getRequestSpec();
        responseSpec = getResponseSpec();
        collectionAction = new CollectionAction();
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

        assertFalse(response.getArtObjects().isEmpty());
    }

    @Test(description = "GET /collection by involvedMaker", groups = {"collections"},
            dataProvider = "artists",
            dataProviderClass = CollectionDataProvider.class)
    public void getCollectionByInvolvedMaker(String artist) {
        CollectionResponse response = collectionAction.getCollectionByParam(
                        Map.of("involvedMaker", artist), requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertThat(response.getArtObjects()
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
                        Map.of(
                                "material", material),
                        requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertFalse(response.getArtObjects().isEmpty());
    }

    @Test(description = "GET /collection by material", groups = {"collections"})
    public void getCollectionBy11111111() {
        CollectionResponse response = collectionAction.getCollectionByParam(
                        Map.of(
                                "material", "karton",
                                "involvedMaker", "Vincent van Gogh"),
                        requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertThat(response.getArtObjects()
                        .stream()
                        .map(ArtObjects::getPrincipalOrFirstMaker)
                        .toList(),
                everyItem(equalTo("Vincent van Gogh")));
    }
}
