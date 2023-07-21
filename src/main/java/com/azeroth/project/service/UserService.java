package com.azeroth.project.service;

import com.azeroth.project.domain.UserDomain;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    // id 값으로 회원정보 가져오기
    UserDomain findById(Long id);

    // 로그인 아이디의 회원 정보 읽어오기
    UserDomain findByUsername(String username);

    // 특정 로그인 아이디의 회원이 존재하는지 확인
    boolean isExist(String username);

    // 신규 회원 등록
    int register(UserDomain user, MultipartFile multipartFile);

    // 회원 정보 수정
    int update(UserDomain user);

    // 회원 정보 삭제
    int delete(Long id);
}
