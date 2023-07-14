package com.azeroth.project.repository;

import com.azeroth.project.domain.CardDomain;

public interface AdminRepository {

    // 카드 조회
    CardDomain find(CardDomain card);
    // 카드 결재
    int update(CardDomain card);

    // 회원 목록 조회
    // 특정 회원 검색
    // 특정 회원이 구매한 상품 list
    // 특정 회원이 작성한 후기 list
    // 답변이 이루어지지 않은 후기 list

}
