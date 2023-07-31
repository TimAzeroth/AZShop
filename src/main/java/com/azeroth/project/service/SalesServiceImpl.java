package com.azeroth.project.service;

import com.azeroth.project.domain.*;
import com.azeroth.project.repository.AddressRepository;
import com.azeroth.project.repository.ProductRepository;
import com.azeroth.project.repository.SalesRepository;
import com.azeroth.project.repository.UserRepository;

import com.azeroth.project.util.U;
import com.azeroth.project.util.Util;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    private SalesRepository salesRepository;

    private UserRepository userRepository;

    private ProductRepository productRepository;

    private AddressRepository addressRepository;

    // 서비스 연결
    @Autowired
    public SalesServiceImpl(SqlSession sqlSession) {
        salesRepository = sqlSession.getMapper(SalesRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);
        System.out.println(getClass().getName() + "() 생성");
    }


    // 주문 / 결제 화면에 정보 불러오기
    @Override
    public SalesDomain saleslist(String u_username) {
        userRepository.findByUsername(u_username);
        SalesDomain salesDomain = salesRepository.findSales(u_username);
        return salesDomain;
    }


    // 결제완료
    @Override
    public int insert(SalesDomain salesDomain) {
        return salesRepository.insert(salesDomain);
    }

    @Override
    public List<SalesInfo> loadSalesed(String username, int rimit) {
        return salesRepository.selectByLastSales(username, rimit);
    }


}
