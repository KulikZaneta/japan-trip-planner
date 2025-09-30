package com.zaneta.japantrip.mapper.city;

import com.zaneta.japantrip.model.City;
import com.zaneta.japantrip.model.dto.attraction.AttractionResponse;
import com.zaneta.japantrip.model.dto.city.CityRequest;
import com.zaneta.japantrip.model.dto.city.CityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(target = "attractions", ignore = true)
    @Mapping(target = "id", ignore = true)
    City toCity(CityRequest requestDTO);

    //CityRequest toCityRequestDto(City city);

    default CityResponse toCityResponse(City city) {
        return new CityResponse(
                city.getId(),
                city.getNameOfCity(),
                city.getNameOfPrefecture(),
                city.getDescription(),
                city.getPopulation(),
                city.getArea(),
                Optional.ofNullable(city.getAttractions())
                        .orElse(Collections.emptySet())
                        .stream()
                        .map(attraction -> new AttractionResponse(
                                attraction.getName(),
                                attraction.getDescription(),
                                attraction.isActive(),
                                attraction.getCity() != null ? attraction.getCity().getId() : null,
                                attraction.getImageUrl(),
                                attraction.getSourceUrl()
                        ))
                        .collect(Collectors.toSet())
        );
    }

    default List<CityResponse> toCityResponseList(List<City> cities) {
        return Optional.ofNullable(cities)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(this::toCityResponse)
                .collect(Collectors.toList());
    }
}
