package com.zaneta.japantrip.repository;

import com.zaneta.japantrip.model.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TripRepository extends MongoRepository<Trip, UUID> {

}
