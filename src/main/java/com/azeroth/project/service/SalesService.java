package com.azeroth.project.service;

import com.azeroth.project.domain.SalesDomain;
import org.springframework.ui.Model;

import java.util.List;

public interface SalesService {

    // 구매자 정보 리스트
    List<SalesDomain> saleslist();
    
    

}
