package com.rijksmuseum.api.models;

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
    private String id;
    private String objectNumber;
    private String title;
    private WebImage webImage;
    private List<PrincipalMaker> principalMakers;
    private String principalMaker;
    private List<String> materials;
    private Dating dating;
    private Label label;
}
