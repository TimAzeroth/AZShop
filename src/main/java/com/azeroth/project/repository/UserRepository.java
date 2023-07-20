package com.azeroth.project.repository;

import com.azeroth.project.domain.AddressDomain;
import com.azeroth.project.domain.AuthorityDomain;
import com.azeroth.project.domain.UserDomain;
import org.springframework.boot.autoconfigure.security.SecurityProperties;


public interface UserRepository {

    // 사용자 PK 값을 이용하여 검색
    UserDomain findById(Long id);
    
    // 사용자 로그인 접속아이디를 이용하여 검색
    UserDomain findByUsername(String username);
    
    // 새로운 사용자 입력
    int insert(UserDomain user);

    // 사용자 정보 수정
    int update(UserDomain user);

    // 계정 삭제
    int delete(Long id);

    // 배송주소 추가
    int postInsert(AddressDomain address);

    // 배송주소 수정
    int postUpdate(AddressDomain address);

    // 배송주소 삭제
    int postDelete(Long id);

}
