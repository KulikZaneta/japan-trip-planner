package com.zaneta.japantrip;

import com.zaneta.japantrip.model.Trip;
import com.zaneta.japantrip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final TripRepository tripRepository;

    @Override
    public void run(String... args) throws Exception {
        Trip trip = Trip.builder()
                .id(UUID.randomUUID())
                .name("test")
                .userId("123e4567-e89b-12d3-a456-426614174000")
                .cityTripList(List.of(
                        Trip.CityTrip.builder()
                                .cityId(1L)
                                .attractionId(List.of(1L, 2L))
                                .build()
                ))
                .build();

        tripRepository.save(trip);
    }
}

