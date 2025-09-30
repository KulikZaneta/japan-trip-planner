package com.zaneta.japantrip.service;

import com.zaneta.japantrip.model.City;

import java.util.List;

public interface CityService {

    City getCityById(Long cityId);

    List<City> getCities();

    City createCity(City city);

    City updateCity(City city);

    void deleteCityById(Long cityId);
}
