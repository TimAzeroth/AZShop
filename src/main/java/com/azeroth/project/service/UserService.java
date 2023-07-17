package com.azeroth.project.service;

import com.azeroth.project.domain.UserDomain;

public interface UserService {

    // 로그인 아이디의 회원 정보 읽어오기
    public UserDomain findByUsername(String username);

    // 특정 로그인 아이디의 회원이 존재하는지 확인
    public boolean isExist(String username);

    // 신규 회원 등록
    public int register(UserDomain user);


}
