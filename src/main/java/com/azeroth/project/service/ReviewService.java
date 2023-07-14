package com.azeroth.project.service;

import com.azeroth.project.domain.ReviewDomain;

import java.util.List;

public interface ReviewService {
    ReviewDomain saveReview(ReviewDomain reviewDomain);
    List<ReviewDomain> getAllReviews();
}
