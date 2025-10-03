package com.zaneta.japantrip.service.impl;

import com.zaneta.japantrip.model.Review;
import com.zaneta.japantrip.repository.ReviewRepository;
import com.zaneta.japantrip.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReview() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("Review with id: " + reviewId + " not found"));
    }

    @Override
    public List<Review> getAllReviewByUserNickname(String nickname) {
        return reviewRepository.findAllByUser_Nickname(nickname);
    }

    @Override
    public Review createReview(Review review) {
//        review.setAttraction(attraction);
//        review.setUser(user);

        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Review review) {
        Review existingReview = reviewRepository.findById(review.getId()).orElseThrow(() -> new EntityNotFoundException("Review with id: " + review.getId() + " not found"));

        existingReview.setUser(review.getUser());
        existingReview.setAttraction(review.getAttraction());
        existingReview.setRating(review.getRating());
        existingReview.setComment(review.getComment());


        return reviewRepository.save(existingReview);
    }

    @Override
    public void deleteReviewById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

}

