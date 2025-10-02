package com.zaneta.japantrip.model.dto.attraction;

import jakarta.validation.constraints.NotBlank;

public record AttractionResponse(

        @NotBlank(message = "Attraction name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        boolean isActive,

        String cityName,

        String imageUrl,

        String sourceUrl

        //List<ReviewResponse> reviews) {}
) {}

