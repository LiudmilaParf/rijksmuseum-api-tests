package com.rijksmuseum.api.models.collectionDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtObject {
    private Links links;
    private String id;
    private String priref;
    private String objectNumber;
    private String language;
    private String title;
    private String copyrightHolder;
    private WebImage webImage;
    private List<Colors> colors;
    private List<Colors> colorsWithNormalization;
    private List<Colors> normalizedColors;
    private List<Colors> normalized32Colors;
    private List<String> materialsThesaurus;
    private List<String> techniquesThesaurus;
    private List<String> productionPlacesThesaurus;
    private List<String> titles;
    private String description;
    private String labelText;
    private List<String> objectTypes;
    private List<String> objectCollection;
    private List<PrincipalMaker> makers;
    private List<PrincipalMaker> principalMakers;
    private String plaqueDescriptionDutch;
    private String plaqueDescriptionEnglish;
    private String principalMaker;
    private String artistRole;
    private List<Object> associations;
    private Acquisition acquisition;
    private List<Object> exhibitions;
    private List<String> materials;
    private List<String> techniques;
    private List<String> productionPlaces;
    private Dating dating;
    private Classification classification;
    private Boolean hasImage;
    private List<Object> historicalPersons;
    private List<Object> inscriptions;
    private List<Object> documentation;
    private List<Object> catRefRPK;
    private String principalOrFirstMaker;
    private List<Dimension> dimensions;
    private List<Object> physicalProperties;
    private String physicalMedium;
    private String longTitle;
    private String subTitle;
    private String scLabelLine;
    private Label label;
    private Boolean showImage;
    private String location;
}
