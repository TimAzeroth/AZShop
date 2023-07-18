package com.azeroth.project.repository;

import com.azeroth.project.domain.ReviewDomain;

public interface ReviewRepository {

    // 리뷰 작성
    int insert (ReviewDomain review);

    // 리뷰 삭제
    int delete(long id);

    // 리뷰 수정
    int update(ReviewDomain review);

    // 리뷰 글을 관리자가 답변
    int addReply(ReviewDomain review);

    // 관리자 답변글 표시
    int makeVisible(long id);

}
