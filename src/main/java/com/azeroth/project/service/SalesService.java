package com.azeroth.project.service;

import com.azeroth.project.domain.SalesDomain;

public interface SalesService {

    // 판매 정보 리스트
    SalesDomain saleslist(String u_username);


    // 결제 완료 Sales 테이블에 정보 입력
    int insert(SalesDomain salesDomain);



}
