package com.azeroth.project.repository;

import com.azeroth.project.domain.OrderData;
import com.azeroth.project.domain.ProductDomain;
import com.azeroth.project.domain.SalesDomain;

import java.util.List;

public interface SalesRepository {

    // 판매 등록(결제 성공)
    int insert(SalesDomain sales);

    // 판매 조회(주문 / 결제)
    SalesDomain findSales(String u_username);

    // 송장 입력
    int inputSalesNum(SalesDomain sales);

    // username 으로 sales 정보(들) 가져오기
    List<OrderData> selectSalesByUsername(String username);

}
