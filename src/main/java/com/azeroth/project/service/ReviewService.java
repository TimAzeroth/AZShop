package com.azeroth.project.service;

import com.azeroth.project.domain.ReviewDomain;

import java.util.List;

public interface ReviewService {

    ReviewDomain saveReview(ReviewDomain reviewDomain);

    List<ReviewDomain> findByProductId(Long productId);

    int deleteByReviewsId(Long reviewId);

    List<ReviewDomain> replyToReview(long reply); // 어드민 응답

}
