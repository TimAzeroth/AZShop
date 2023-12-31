package com.azeroth.project.repository;

import com.azeroth.project.domain.*;

import java.util.List;

public interface AdminRepository {

    // 카드 조회
    CardDomain findCard(CardDomain card);
    // 카드 결재
    int updateCard(CardDomain card);

    // 회원 목록 조회
    List<UserDomain> ListByUsers();

    // 특정 회원 검색
    UserDomain findByUser(String username);

    // 특정 회원이 구매한 상품 list
    List<userBuyDetail> userBuyProduct(String username);
    // 특정 회원이 구매한 구매 list
    List<SalesDomain> userBuyList(String username);

    // 특정 회원이 작성한 후기 list
    List<adminReview> writeReviewByUser(String username);

    // 답변이 이루어지지 않은 후기 list
    List<ReviewDomain> NoAnswerReview();

    // 상품관리 리스트용 상품리스트
    List<ProductManagement> ProductManagementList();

    int AnswerReview (Long id, String reply);

    // 상품 순위 변경
    int pRankUpdate(Long product_id, Long prank);

}
