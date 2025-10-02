package com.zaneta.japantrip.service;

import com.zaneta.japantrip.model.Attraction;

import java.util.List;

public interface AttractionService {

    Attraction getAttractionById(Long attractionId);

    List<Attraction> getAttractionList();

    Attraction createAttraction(Attraction attraction, String nameCity);

    Attraction updateAttraction(Attraction attraction);

    void deleteAttractionById(Long attractionId);
}
