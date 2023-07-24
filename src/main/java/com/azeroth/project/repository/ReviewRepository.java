package com.azeroth.project.repository;

import com.azeroth.project.domain.ReviewDomain;

import java.util.List;

public interface ReviewRepository {

    // 리뷰 읽기
    int insert(ReviewDomain review);


    // 리뷰 글을 관리자가 답변
    int addReply(ReviewDomain review);

    // 관리자 답변글 표시
    int makeVisible(long id);

    // 리뷰 작성
    List<ReviewDomain> findByProductId();

    // 리뷰 삭제
    int deleteByReviewId(long reviewId);

    // 어드민 응답
    int replyToReview(long reply);

    // 페이징
    int paginationPage(long start, long end);





}