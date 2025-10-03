package com.zaneta.japantrip.mapper.review;

import com.zaneta.japantrip.model.Review;
import com.zaneta.japantrip.model.dto.review.ReviewRequest;
import com.zaneta.japantrip.model.dto.review.ReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "attraction", ignore = true)
    Review toReview(ReviewRequest request);

    @Mapping(target = "nameOfUser", source = "user.nickname")
    @Mapping(target = "nameOfAttraction", source = "attraction.name")
    ReviewResponse toReviewResponse(Review review);

    default List<ReviewResponse> toReviewsList(List<Review> reviews) {
        return Optional.ofNullable(reviews)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(this::toReviewResponse)
                .toList();
    }
}
