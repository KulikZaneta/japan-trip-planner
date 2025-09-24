package com.zaneta.japantrip.model.dto.trip;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record TripDTO(UUID id, String name, LocalDate tripDate, String userId, List<CityTripDTO> cityTrips) {
    public record CityTripDTO(Long cityId, Set<Long> attractionId) {}

}

