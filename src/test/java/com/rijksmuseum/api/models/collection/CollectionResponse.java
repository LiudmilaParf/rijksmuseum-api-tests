package com.rijksmuseum.api.models.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionResponse {
    private int elapsedMilliseconds;
    private int count;
    private CountFacets countFacets;
    private List<ArtObjects> artObjects;
    private List<Facets> facets;
}
