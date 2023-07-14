package com.azeroth.project.controller;


import com.azeroth.project.domain.ReviewDomain;
import com.azeroth.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ReviewDomain createReview(ReviewDomain reviewDomain){
        return reviewService.saveReview(review);
    }

    @GetMapping
    public List<ReviewDomain> getAllReviews(){
        return reviewService.getAllReviews();
    }



}
