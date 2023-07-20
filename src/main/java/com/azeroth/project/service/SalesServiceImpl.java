package com.azeroth.project.service;

import com.azeroth.project.domain.SalesDomain;
import com.azeroth.project.repository.SalesRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    private SalesRepository salesRepository;

    // 서비스 연결
    @Autowired
    public SalesServiceImpl(SqlSession sqlSession) {
        salesRepository = sqlSession.getMapper(SalesRepository.class);
    }


    // 주문 / 결제 화면에 정보 불러오기
    @Override
    public SalesDomain saleslist(String u_username) {
        SalesDomain salesDomain = salesRepository.findSales(u_username);
        return salesDomain;
    }

    @Override
    public int insert(SalesDomain salesDomain) {
        
        return 0;
    }


}
