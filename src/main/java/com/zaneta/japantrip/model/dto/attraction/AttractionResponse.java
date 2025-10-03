package com.zaneta.japantrip.model.dto.attraction;

public record AttractionResponse(Long id, String name, String description, boolean isActive, String cityName,
                                 String imageUrl, String sourceUrl //,List<ReviewResponse> reviews
) {
}

