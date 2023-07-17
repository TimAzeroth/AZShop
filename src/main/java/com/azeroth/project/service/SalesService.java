package com.azeroth.project.service;

import com.azeroth.project.domain.SalesDomain;
import org.springframework.ui.Model;

import java.util.List;

public interface SalesService {

    SalesDomain sales(SalesDomain salesDomain);

    // 판매 정보 리스트
    List<SalesDomain> saleslist();


}
