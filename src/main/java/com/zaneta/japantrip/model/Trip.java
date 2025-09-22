package com.zaneta.japantrip.model;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "trips")
public class Trip {

    @Id
    private UUID id;

    @Field("trip_name")
    @NotBlank(message = "Trip name is required")
    private String name;

    @Field("trip_date")
    private LocalDate tripDate;

    @Field("user_id")
    @NotBlank(message = "User id is required")
    private String userId;

    @Field("city_trip_list")
    private List<@Valid CityTrip> cityTripList = new ArrayList<>();


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CityTrip {
        @Field("city_id")
        @NotNull(message = "City id is required")
        private Long cityId;

        @Field("attraction_id")
        private Set<Long> attractionId = new LinkedHashSet<>();
    }
}

