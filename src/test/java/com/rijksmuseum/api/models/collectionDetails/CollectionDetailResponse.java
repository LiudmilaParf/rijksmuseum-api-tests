package com.rijksmuseum.api.models.collectionDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDetailResponse {
    private int elapsedMilliseconds;
    private ArtObject artObject;
    private ArtObjectPage artObjectPage;
}
