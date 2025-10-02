package com.zaneta.japantrip.mapper.attraction;

import com.zaneta.japantrip.model.Attraction;
import com.zaneta.japantrip.model.dto.attraction.AttractionRequest;
import com.zaneta.japantrip.model.dto.attraction.AttractionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface AttractionMapper {

    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "city", ignore = true)
    Attraction toAttraction(AttractionRequest request);

    //TODO lista jako attractionresponse -> kolekcje opinii
    @Mapping(target = "cityName", source = "city.nameOfCity")
    @Mapping(target = "isActive", expression = "java(attraction.isActive())")
    AttractionResponse toAttractionResponse(Attraction attraction);

    default List<AttractionResponse> toAttractionResponseList(List<Attraction> attractions) {
        return Optional.ofNullable(attractions)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(this::toAttractionResponse)
                .toList();
    }
}
