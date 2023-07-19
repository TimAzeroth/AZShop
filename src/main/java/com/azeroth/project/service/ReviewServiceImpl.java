package com.azeroth.project.service;

import com.azeroth.project.domain.ReviewDomain;
import com.azeroth.project.repository.ReviewRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewRepository reviewRepository;
    @Autowired
    public ReviewServiceImpl(SqlSession sqlSession) {
        reviewRepository=sqlSession.getMapper(ReviewRepository.class);
    }
    @Override
    public ReviewDomain saveReview(ReviewDomain reviewDomain) {
        return null;
    }

    @Override
    public List<ReviewDomain> findByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public int deleteByReviewsId(Long reviewId) {
       return reviewRepository.deleteByReviewId(reviewId);

    }



}
