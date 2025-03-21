package com.rijksmuseum.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeaderImage {
    private String guid;
    private int offsetPercentageX;
    private int offsetPercentageY;
    private int width;
    private int height;
    private String url;
}
