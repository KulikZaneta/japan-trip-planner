package com.zaneta.japantrip.model.dto.attraction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttractionRequest {

    private String name;

    private String description;

    private String cityName;

    private String imageUrl;

    private String sourceUrl;

    //private List<ReviewDTO> reviews;
}
