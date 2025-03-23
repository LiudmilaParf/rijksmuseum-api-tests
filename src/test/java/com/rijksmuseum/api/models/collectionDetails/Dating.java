package com.rijksmuseum.api.models.collectionDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dating {
    private String presentingDate;
    private Integer sortingDate;
    private Integer period;
    private Integer yearEarly;
    private Integer yearLate;
}
