package com.azeroth.project.service;

import com.azeroth.project.domain.ReviewDomain;
import org.springframework.ui.Model;

import java.util.List;

public interface ReviewService {

    int saveReview(ReviewDomain reviewDomain);

    List<ReviewDomain> findByProductId (Integer page, Long productId, Model model);

    int deleteByReviewsId(Long reviewId);

    int replyToReview(long reply); // 어드민 응답



}
