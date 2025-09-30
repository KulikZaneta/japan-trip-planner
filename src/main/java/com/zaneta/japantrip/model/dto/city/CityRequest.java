package com.zaneta.japantrip.model.dto.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityRequest {

    @NotBlank(message = "City name is required")
    private String nameOfCity;

    private String nameOfPrefecture;

    @NotBlank(message = "Description is required")
    private String description;

    private Long population;

    @NotNull(message = "Area is required")
    private Double area;

}

