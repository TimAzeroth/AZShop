package com.azeroth.project.service;

import com.azeroth.project.domain.ReviewDomain;
import com.azeroth.project.repository.ReviewRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Value("${app.pagination.write_ADMIN}")
    private int pagination_wirte;


    @Value("${app.pagination.page_pagination}")
    private  int pagination_page;


    private ReviewRepository reviewRepository;


    @Autowired
    public ReviewServiceImpl(SqlSession sqlSession) {
        reviewRepository=sqlSession.getMapper(ReviewRepository.class);
    }
    @Override
    public int saveReview(ReviewDomain reviewDomain) {
       return reviewRepository.insert(reviewDomain);
    }

    @Override
    public List<ReviewDomain> findByProductId(Integer page, Long productId, Model model) {
            return reviewRepository.findByProductId();
        }

    @Override
    public int deleteByReviewsId(Long reviewId) {
       return reviewRepository.deleteByReviewId(reviewId);

    }

    @Override
    public int replyToReview(long reply) {
         return reviewRepository.replyToReview(reply);
    }   // 어드민 응답

}
