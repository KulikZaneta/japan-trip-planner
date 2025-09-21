package com.zaneta.japantrip.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "trips")
public class Trip {

    @Id
    private UUID id;

    private String name;

    private String userId;

    private List<CityTrip> cityTripList;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CityTrip {
        private Long cityId;

        private List<Long> attractionId;
    }
}

