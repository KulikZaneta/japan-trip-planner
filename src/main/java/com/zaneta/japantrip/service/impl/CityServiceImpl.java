package com.zaneta.japantrip.service.impl;

import com.zaneta.japantrip.model.City;
import com.zaneta.japantrip.repository.CityRepository;
import com.zaneta.japantrip.service.CityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException("City with id " + cityId + " not found"));
    }

    @Override
    public List<City> getCities() {
        return cityRepository.findAll();
    }

    @Transactional
    @Override
    public City createCity(City city) {
        cityRepository.findByNameOfCity(city.getNameOfCity())
                .ifPresent(c -> {
                    throw new IllegalArgumentException("City with name " + c.getNameOfCity() + " already exists");
                });
        return cityRepository.save(city);
    }

    @Override
    public City updateCity(City city) {
        City existingCity = cityRepository.findById(city.getId()).orElseThrow(() -> new EntityNotFoundException("City with id :" + city.getId() + " not found"));

        existingCity.setNameOfCity(city.getNameOfCity());
        existingCity.setNameOfPrefecture(city.getNameOfPrefecture());
        existingCity.setDescription(city.getDescription());
        existingCity.setPopulation(city.getPopulation());
        existingCity.setArea(city.getArea());

        return cityRepository.save(existingCity);
    }

    @Override
    public void deleteCityById(Long cityId) {
        cityRepository.deleteById(cityId);

    }
}

