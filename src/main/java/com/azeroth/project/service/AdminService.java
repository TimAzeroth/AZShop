package com.azeroth.project.service;

import com.azeroth.project.domain.*;

import java.util.List;

public interface AdminService {
    // Spring Security 처리

    // 로그인 처리

    // 고객 관리 기능 ( 권한 설정등 )
    UserDomain UpdateUserInfo();


    // 회원 LIST ( 가입된 일반 사용자 LIST 출력)
    List<UserDomain> findByAllUser();

    // 회원 검색 ( ID / 닉네임 / EMAIL 등)
    UserDomain findByUsername(String username);

    // 특정 회원이 구매한 상품 LIST
    List<userBuyDetail> selectBuyByUser(String username);

    // 특정 회원이 작성한 후기 LIST
    List<adminReview> selectAnswerByUser(String username);

    // 답변 미 작성 된 후기 LIST ( 답변을 달 수 있도록 처리)
    List<ReviewDomain> selectNoAnswerReview();

    // 사용자 권한 조회
    List<AuthorityDomain>  selectAuthoritiesById(Long id);

    // 결재 chk
    public SalesChkDomain salesCHK (CardDomain card);

    // 상품관리를 위한 List
    List<ProductManagement> ProductManagementList();

    // 로그인 발생시 로그인 일자 update
    void updateLogDate(UserDomain userDomain);

    // 답변작성시 update
    int answerReview(Long id, String reply);

    int pRankUpdate(Long product_id, Long prank);
}
