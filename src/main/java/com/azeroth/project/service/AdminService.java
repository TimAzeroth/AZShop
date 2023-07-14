package com.azeroth.project.service;

import com.azeroth.project.domain.AuthorityDomain;

import java.util.List;

public interface AdminService {
    // Spring Security 처리
    // 로그인 처리
    // 고객 관리 기능
    // 회원 LIST ( 가입된 일반 사용자 LIST 출력)
    // 회원 검색 ( ID / 닉네임 / EMAIL 등)
    // 특정 회원이 구매한 상품 LIST
    // 특정 회원이 작성한 후기 LIST
    // 답변 미 작성 된 후기 LIST ( 답변을 달 수 있도록 처리)
    // 사용자 권한 조회
    List<AuthorityDomain>  selectAuthoritiesById(Long id);


}
