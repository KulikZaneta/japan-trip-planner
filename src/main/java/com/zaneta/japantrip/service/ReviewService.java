package com.zaneta.japantrip.service;

import com.zaneta.japantrip.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReview();

    Review getReviewById(Long id);

    List<Review> getAllReviewByUserNickname(String nickname);

    Review createReview(Review review);

    Review updateReview(Review review);

    void deleteReviewById(Long id);

}
