package com.azeroth.project.repository;

import com.azeroth.project.domain.ProductDomain;
import com.azeroth.project.domain.SalesDomain;

import java.util.List;

public interface SalesRepository {

    // 판매 등록(결제 성공)
    int insert(SalesDomain sales);

    // 판매 조회(주문 / 결제)
    SalesDomain findSales(Long id);

    // 송장 입력
    int inoutSalesNum(SalesDomain sales);

    // 특정 판매 조회(결제 완료)
    SalesDomain find(Long id);

}
