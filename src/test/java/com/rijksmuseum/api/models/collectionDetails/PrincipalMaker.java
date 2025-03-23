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
public class PrincipalMaker {
    private String name;
    private String unFixedName;
    private String placeOfBirth;
    private String dateOfBirth;
    private String dateOfBirthPrecision;
    private String dateOfDeath;
    private String dateOfDeathPrecision;
    private String placeOfDeath;
    private List<String> occupation;
    private List<String> roles;
    private String nationality;
    private String biography;
    private List<String> productionPlaces;
    private String qualification;
    private String labelDesc;
}
