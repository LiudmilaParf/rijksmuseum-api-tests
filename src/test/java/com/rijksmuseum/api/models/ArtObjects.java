package com.rijksmuseum.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtObjects {
    private Links links;
    private String id;
    private String objectNumber;
    private String title;
    private Boolean hasImage;
    private String principalOrFirstMaker;
    private String longTitle;
    private Boolean showImage;
    private Boolean permitDownload;
    private WebImage webImage;
    private HeaderImage headerImage;
    private List<String> productionPlaces;
}
