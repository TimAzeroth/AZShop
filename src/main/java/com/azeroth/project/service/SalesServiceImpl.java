package com.azeroth.project.service;

import com.azeroth.project.domain.SalesDomain;
import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.repository.SalesRepository;
import com.azeroth.project.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    }


    // 주문 / 결제 화면에 정보 불러오기
    @Override
    public SalesDomain saleslist(String username) {
        SalesDomain salesDomain = salesRepository.findSales(username);
        return salesDomain;
    }

    @Override
    public int insert(SalesDomain salesDomain) {
        // session정보 땜빵
        UserDomain user = null;

        user.setId(1L);
        user.setAuthority_id(1L);
        user.setUsername("user1");
        user.setNickname("사용자1");
        user.setEmail("user1@gmail.com");
        user.setProfileimg("face01.png");
        user.setU_status("USE");
        user.setPhone("01012341234");

        // 위 정보는 session 의 정보,  DB 에서 다시 읽어온다
        user = userRepository.findByUsername(user.getUsername());
        salesDomain.setUser(user);

        // UserDomain userDomain = (유틸).getLoggedUser();

        return salesRepository.insert(salesDomain);
    }




}
