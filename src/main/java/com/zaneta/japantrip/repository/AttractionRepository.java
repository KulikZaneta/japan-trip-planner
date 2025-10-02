package com.zaneta.japantrip.repository;

import com.zaneta.japantrip.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    Optional<Attraction> findAttractionByName(String nameOfAttraction);
}
