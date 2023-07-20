package com.azeroth.project.repository;

import com.azeroth.project.domain.AuthorityDomain;

public interface AuthorityRepository {

    // 특정 이름 의 권한 정보 읽어오기
    AuthorityDomain findByName(String name);


}
