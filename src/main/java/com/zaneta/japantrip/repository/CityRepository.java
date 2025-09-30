package com.zaneta.japantrip.repository;

import com.zaneta.japantrip.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByNameOfCity(String nameOfCity);


}
