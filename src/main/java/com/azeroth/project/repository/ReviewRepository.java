package com.azeroth.project.repository;

import com.azeroth.project.domain.ReviewDomain;

public interface ReviewRepository {

    // 리뷰 작성
    int insert (ReviewDomain review);

    // 리뷰 삭제
    int delete(long id);

    // 리뷰 수정
    int update(ReviewDomain review);

}
