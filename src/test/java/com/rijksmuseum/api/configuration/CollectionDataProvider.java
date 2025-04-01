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

    @DataProvider(name = "material")
    public static Object[][] getMaterial() {
        return new Object[][]{
                {"canvas"},
                {"karton"},
                {"papier"},
                {"gouache (waterverf)"}
        };
    }

    @DataProvider(name = "objectTypes")
    public static Object[][] getObjectTypes() {
        return new Object[][]{
                {"schilderij"},
                {"prent"},
                {"foto"}
        };
    }

    @DataProvider(name = "techniques")
    public static Object[][] getTechniques() {
        return new Object[][]{
                {"olieverf"},
                {"etsen"},
                {"lithografie"}
        };
    }

    @DataProvider(name = "periods")
    public static Object[][] getPeriods() {
        return new Object[][]{
                {"17"},
                {"18"},
                {"19"}
        };
    }
}
