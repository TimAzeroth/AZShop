package com.azeroth.project.service;

import com.azeroth.project.domain.SalesDomain;
import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.repository.SalesRepository;
import com.azeroth.project.repository.UserRepository;

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

    // 서비스 연결
    @Autowired
    public SalesServiceImpl(SqlSession sqlSession) {
        salesRepository = sqlSession.getMapper(SalesRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);
        System.out.println(getClass().getName() + "생성");
    }


    // 주문 / 결제 화면에 정보 불러오기
    @Override
    public SalesDomain saleslist(String u_username) {
        userRepository.findByUsername(u_username);
        SalesDomain salesDomain = salesRepository.findSales(u_username);
        return salesDomain;
    }

//    @Override
//    public int insert(SalesDomain salesDomain) {
//        // session정보 땜빵
//        UserDomain user = null;
//
//        user.setId(1L);
//        user.setAuthority_id(1L);
//        user.setUsername("user1");
//        user.setNickname("사용자1");
//        user.setEmail("user1@gmail.com");
//        user.setProfileimg("face01.png");
//        user.setU_status("USE");
//        user.setPhone("01012341234");
//
//        // 위 정보는 session 의 정보,  DB 에서 다시 읽어온다
//        user = userRepository.findByUsername(user.getUsername());
//        salesDomain.setUser(user);
//
//        int cnt = salesRepository.insert(salesDomain);
//
//        // UserDomain userDomain = (유틸).getLoggedUser();
//
//        return salesRepository.insert(salesDomain);
//    }




}
