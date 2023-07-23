package com.azeroth.project.repository;

import com.azeroth.project.domain.AuthorityDomain;
import com.azeroth.project.domain.UserDomain;

import java.util.List;

public interface AuthorityRepository {

    // 특정 이름 의 권한 정보 읽어오기
    AuthorityDomain findByName(String name);

    // 특정 사용자(User) 의 권한 읽어오기
    List<AuthorityDomain> findByUser(UserDomain user);

}
