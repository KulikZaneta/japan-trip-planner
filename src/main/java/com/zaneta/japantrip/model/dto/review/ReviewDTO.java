package com.zaneta.japantrip.model.dto.review;

import com.zaneta.japantrip.model.Attraction;
import com.zaneta.japantrip.model.User;

public record ReviewDTO(Long id, User user, Attraction attraction, Long rating, String comment) {

}

