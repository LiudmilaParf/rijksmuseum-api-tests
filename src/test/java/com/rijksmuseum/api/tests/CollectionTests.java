package com.rijksmuseum.api.tests;

import com.rijksmuseum.api.configuration.CollectionDataProvider;
import com.rijksmuseum.api.models.ArtObjects;
import com.rijksmuseum.api.models.CollectionResponse;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.rijksmuseum.api.utilities.Route.COLLECTION;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertFalse;

@Story("RM-1 RM API: GetCollections")
public class CollectionTests extends BaseTest {

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
            dataProviderClass = CollectionDataProvider.class
    )
    public void getCollectionByInvolvedMaker(String artist) {
        CollectionResponse response = given()
                .spec(getRequestSpec())
                .queryParam("involvedMaker", artist)
                .when()
                .get(COLLECTION)
                .then()
                .statusCode(SC_OK)
                .extract()
                .as(CollectionResponse.class);

        assertThat(response.getArtObjects().stream()
                        .map(ArtObjects::getPrincipalOrFirstMaker)
                        .toList(),
                everyItem(equalTo(artist))
        );
    }
}
