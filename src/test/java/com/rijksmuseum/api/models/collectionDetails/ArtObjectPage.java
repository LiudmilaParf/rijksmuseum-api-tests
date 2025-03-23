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
public class ArtObjectPage {
    private String id;
    private List<Object> similarPages;
    private String lang;
    private String objectNumber;
    private List<Object> tags;
    private String plaqueDescription;
    private String audioFile1;
    private String audioFileLabel1;
    private String audioFileLabel2;
    private String createdOn;
    private String updatedOn;
    private AdlibOverrides adlibOverrides;
}
