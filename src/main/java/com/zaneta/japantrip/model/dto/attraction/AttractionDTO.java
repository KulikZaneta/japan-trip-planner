package com.zaneta.japantrip.model.dto.attraction;

import com.zaneta.japantrip.model.City;
import com.zaneta.japantrip.model.Review;

import java.util.List;

public record AttractionDTO(Long id, String name, String description, City city, String imageUrl, String sourceUrl, List<Review> reviews) {
}
