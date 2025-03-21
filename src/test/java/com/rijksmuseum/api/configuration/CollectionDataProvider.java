package com.rijksmuseum.api.configuration;

import org.testng.annotations.DataProvider;

public class CollectionDataProvider {

    @DataProvider(name = "artists")
    public static Object[][] getArtist() {
        return new Object[][]{
                {"Rembrandt van Rijn"},
                {"Johannes Vermeer"},
                {"Vincent van Gogh"}
        };
    }
}
