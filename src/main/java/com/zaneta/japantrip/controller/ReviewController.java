package com.zaneta.japantrip.controller;

import com.zaneta.japantrip.mapper.review.ReviewMapper;
import com.zaneta.japantrip.model.Attraction;
import com.zaneta.japantrip.model.Review;
import com.zaneta.japantrip.model.User;
import com.zaneta.japantrip.model.dto.review.ReviewRequest;
import com.zaneta.japantrip.model.dto.review.ReviewResponse;
import com.zaneta.japantrip.service.AttractionService;
import com.zaneta.japantrip.service.ReviewService;
import com.zaneta.japantrip.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    private final UserService userService;

    private final AttractionService attractionService;

    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewService reviewService, UserService userService, AttractionService attractionService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.attractionService = attractionService;
        this.reviewMapper = reviewMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Long id) {
        Review reviewById = reviewService.getReviewById(id);
        ReviewResponse reviewResponse = reviewMapper.toReviewResponse(reviewById);

        return ResponseEntity.ok(reviewResponse);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAllReview() {
        List<Review> allReview = reviewService.getAllReview();
        List<ReviewResponse> reviewResponses = reviewMapper.toReviewsList(allReview);

        return ResponseEntity.ok(reviewResponses);
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<List<ReviewResponse>> getAllReviewByUserNickname(@PathVariable String nickname) {
        List<Review> allReviewByUserNickname = reviewService.getAllReviewByUserNickname(nickname);
        List<ReviewResponse> reviewResponses = reviewMapper.toReviewsList(allReviewByUserNickname);

        return ResponseEntity.ok(reviewResponses);
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestBody @Valid ReviewRequest request) {
        User userById = userService.getUserById(request.userId());
        Attraction attractionById = attractionService.getAttractionById(request.attractionId());

        Review review = reviewMapper.toReview(request);
        review.setUser(userById);
        review.setAttraction(attractionById);

        Review savedReview = reviewService.createReview(review);
        ReviewResponse reviewResponse = reviewMapper.toReviewResponse(savedReview);

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable Long id, @RequestBody @Valid ReviewRequest request) {
        Review review = reviewMapper.toReview(request);
        review.setId(id);

        Review updateReview = reviewService.updateReview(review);
        ReviewResponse reviewResponse = reviewMapper.toReviewResponse(updateReview);

        return ResponseEntity.ok(reviewResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable Long id) {
        reviewService.deleteReviewById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

