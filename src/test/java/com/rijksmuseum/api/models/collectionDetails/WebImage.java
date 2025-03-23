package com.rijksmuseum.api.models.collectionDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebImage {
    private String guid;
    private int offsetPercentageX;
    private int offsetPercentageY;
    private int width;
    private int height;
    private String url;
}
