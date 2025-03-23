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
public class Classification {
    private List<String> iconClassIdentifier;
    private List<String> iconClassDescription;
    private List<Object> motifs;
    private List<Object> events;
    private List<Object> periods;
    private List<String> places;
    private List<Object> people;
    private List<String> objectNumbers;
}
