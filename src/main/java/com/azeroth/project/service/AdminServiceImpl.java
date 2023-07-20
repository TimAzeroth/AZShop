package com.azeroth.project.service;

import com.azeroth.project.domain.*;
import com.azeroth.project.repository.AdminRepository;
import com.azeroth.project.domain.SalesChkDomain;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    private SalesChkDomain schk;

    @Autowired
    public AdminServiceImpl(SqlSession sqlSession) {

        adminRepository = sqlSession.getMapper(AdminRepository.class);
    }

    @Override
    public UserDomain UpdateUserInfo() {

        return null;
    }

    @Override
    public List<UserDomain> findByAllUser() {

        return adminRepository.ListByUsers();
    }

    @Override
    public UserDomain findByUsername(String username) {

        return adminRepository.findByUser(username);
    }

    @Override
    public List<SalesDomain> selectBuyByUser(String username) {
        return null;
    }

    @Override
    public List<ReviewDomain> selectAnswerByUser(String username) {
        return null;
    }

    @Override
    public List<ReviewDomain> selectNoAnswerReview() {
        return null;
    }

    @Override
    public List<AuthorityDomain> selectAuthoritiesById(Long id) {
        return null;
    }

    public SalesChkDomain salesCHK (CardDomain card){

        CardDomain payCard = adminRepository.findCard(card);
        schk.setChkProcess(false);
        schk.setEreMag("정상 처리되었습니다.");


        if(payCard == null) {
            schk.setEreMag("잘못된 카드정보 입니다.");
        }
        if(payCard.getBalance() < card.getBalance()){
            schk.setEreMag("잔액이 부족합니다.");
        }

        if (schk.getEreMag().equals("정상 처리되었습니다.")){
            Long balance = card.getBalance();
            Long payment = payCard.getBalance();
            payCard.setBalance(balance-payment);
            adminRepository.updateCard(payCard);
            schk.setChkProcess(true);
        }
        return schk;
    }
}
