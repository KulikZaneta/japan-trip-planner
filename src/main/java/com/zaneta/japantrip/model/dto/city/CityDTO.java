package com.zaneta.japantrip.model.dto.city;

import com.zaneta.japantrip.model.Attraction;

import java.util.Set;

public record CityDTO(Long id, String nameOfCity, String nameOfPrefecture, String description, Long population, Double area, Set<Attraction> attractions) {

}

