package com.zaneta.japantrip.controller;

import com.zaneta.japantrip.mapper.city.CityMapper;
import com.zaneta.japantrip.model.City;
import com.zaneta.japantrip.model.dto.city.CityRequest;
import com.zaneta.japantrip.model.dto.city.CityResponse;
import com.zaneta.japantrip.service.CityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    private final CityMapper cityMapper;

    public CityController(CityService cityService, CityMapper cityMapper) {
        this.cityService = cityService;

        this.cityMapper = cityMapper;
    }

    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCities() {
        List<City> cities = cityService.getCities();
        List<CityResponse> cityResponses = cityMapper.toCityResponseList(cities);

        return ResponseEntity.ok(cityResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable Long id) {
        City city = cityService.getCityById(id);
        CityResponse cityResponse = cityMapper.toCityResponse(city);

        return ResponseEntity.ok(cityResponse);
    }

    @PostMapping
    public ResponseEntity<CityResponse> createCity(@RequestBody @Valid CityRequest request) {
        City city = cityMapper.toCity(request);
        City savedCity = cityService.createCity(city);
        CityResponse cityResponse = cityMapper.toCityResponse(savedCity);

        return ResponseEntity.status(HttpStatus.CREATED).body(cityResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(@PathVariable Long id, @RequestBody @Valid CityRequest request) {
        City city = cityMapper.toCity(request);
        city.setId(id);

        City updateCity = cityService.updateCity(city);
        CityResponse cityResponse = cityMapper.toCityResponse(updateCity);

        return ResponseEntity.ok(cityResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCityById(@PathVariable Long id) {
        cityService.deleteCityById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

