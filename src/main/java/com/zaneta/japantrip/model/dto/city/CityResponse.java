package com.zaneta.japantrip.model.dto.city;

import com.zaneta.japantrip.model.dto.attraction.AttractionResponse;

import java.util.Set;

public record CityResponse(Long id, String nameOfCity, String nameOfPrefecture, String description, Long population, Double area, Set<AttractionResponse> attractions) {
}
