package com.rijksmuseum.api.models.collectionDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Label {
    private String title;
    private String makerLine;
    private String description;
    private String notes;
    private String date;
}
