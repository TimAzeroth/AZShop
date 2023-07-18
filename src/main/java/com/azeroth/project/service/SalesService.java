package com.azeroth.project.service;

import com.azeroth.project.domain.SalesDomain;
import org.springframework.ui.Model;

import java.util.List;

public interface SalesService {

    // 판매 정보 리스트
    SalesDomain saleslist(Long id);


}
