package com.zaneta.japantrip.service.impl;

import com.zaneta.japantrip.model.Attraction;
import com.zaneta.japantrip.model.City;
import com.zaneta.japantrip.repository.AttractionRepository;
import com.zaneta.japantrip.repository.CityRepository;
import com.zaneta.japantrip.service.AttractionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;

    private final CityRepository cityRepository;

    public AttractionServiceImpl(AttractionRepository attractionRepository, CityRepository cityRepository) {
        this.attractionRepository = attractionRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public Attraction getAttractionById(Long attractionId) {
        return attractionRepository.findById(attractionId).orElseThrow(() -> new EntityNotFoundException("Attraction with id " + attractionId + " not found"));
    }

    @Override
    public List<Attraction> getAttractionList() {
        return attractionRepository.findAll();
    }

    @Transactional
    @Override
    public Attraction createAttraction(Attraction attraction, String cityName) {
        City city = cityRepository.findByNameOfCity(cityName).orElseThrow(() -> new EntityNotFoundException("City with name: " + cityName + " not found"));

        attractionRepository.findAttractionByName(attraction.getName()).ifPresent(a -> {
            throw new IllegalArgumentException("Attraction with name " + a.getName() + " already exists");
        });
        attraction.setCity(city);
        attraction.setActive(true);

        return attractionRepository.save(attraction);
    }

    @Override
    public Attraction updateAttraction(Attraction attraction) {
        Attraction existingAttraction = attractionRepository.findById(attraction.getId()).orElseThrow(() -> new EntityNotFoundException("Attraction with id: " + attraction.getId() + "not found"));

        existingAttraction.setName(attraction.getName());
        existingAttraction.setDescription(attraction.getDescription());
        existingAttraction.setCity(attraction.getCity());
        existingAttraction.setActive(attraction.isActive());
        existingAttraction.setImageUrl(attraction.getImageUrl());
        existingAttraction.setSourceUrl(attraction.getSourceUrl());

        return attractionRepository.save(existingAttraction);
    }

    @Override
    public void deleteAttractionById(Long attractionId) {
        attractionRepository.deleteById(attractionId);
    }
}

