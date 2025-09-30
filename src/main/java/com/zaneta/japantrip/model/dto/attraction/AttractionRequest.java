package com.zaneta.japantrip.model.dto.attraction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttractionRequest {

    private String name;

    private String description;

    private List<Long> cityId;

    private String imageUrl;

    private String sourceUrl;

    //private List<ReviewDTO> reviews;
}
