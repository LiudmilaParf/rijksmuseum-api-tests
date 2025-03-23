package com.rijksmuseum.api.tests;

import com.rijksmuseum.api.actions.CollectionAction;
import com.rijksmuseum.api.actions.CollectionDetailsAction;
import com.rijksmuseum.api.configuration.CollectionConfiguration;
import com.rijksmuseum.api.models.collectionDetails.CollectionDetailResponse;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class CollectionDetailsTests extends BaseTest {

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    CollectionAction collectionAction;
    CollectionDetailsAction collectionDetailsAction;
    CollectionConfiguration collectionConfig;
    String objectNumber;

    @BeforeMethod(groups = {"smoke", "collectionDetails"})
    public void init() {
        requestSpec = getRequestSpec();
        responseSpec = getResponseSpec();
        collectionAction = new CollectionAction();
        collectionDetailsAction = new CollectionDetailsAction();
        collectionConfig = new CollectionConfiguration();
    }

    @Step
    public void getObjectNumber() {
        objectNumber = collectionAction.getRandomObjectNumber(collectionConfig.getInvolvedMaker(), requestSpec, responseSpec);
    }

    @Test(description = "GET /collection", groups = {"smoke", "collectionDetails"})
    public void getCollectionDetailsByObjectNumber() {
        getObjectNumber();

        CollectionDetailResponse response = collectionDetailsAction.getCollectionDetailsByObjectNumber(
                objectNumber, requestSpec, responseSpec)
                .statusCode(SC_OK)
                .extract()
                .as(CollectionDetailResponse.class);
    }
}
