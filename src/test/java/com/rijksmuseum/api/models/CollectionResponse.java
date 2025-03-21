package com.rijksmuseum.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionResponse {
    private int elapsedMilliseconds;
    private int count;
    private List<ArtObjects> artObjects;
}
