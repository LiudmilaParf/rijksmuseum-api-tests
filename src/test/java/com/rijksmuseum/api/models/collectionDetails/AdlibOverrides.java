package com.rijksmuseum.api.models.collectionDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdlibOverrides {
    private String titel;
    private String maker;
    private String etiketText;
}
