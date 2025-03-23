package com.rijksmuseum.api.models.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountFacets {
    private Integer hasimage;
    private Integer ondisplay;
}
