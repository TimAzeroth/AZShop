package com.azeroth.project.service;

import com.azeroth.project.domain.ReviewDomain;
import com.azeroth.project.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewRepository reviewRepository;

    @Override
    public ReviewDomain saveReview(ReviewDomain reviewDomain) {
        return null;
    }

    @Override
    public List<ReviewDomain> getAllReviews() {
        return null;
    }
}
