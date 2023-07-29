package com.azeroth.project.service;

import com.azeroth.project.domain.*;
import com.azeroth.project.repository.AdminRepository;
import com.azeroth.project.domain.SalesChkDomain;
import com.azeroth.project.repository.AuthorityRepository;
import com.azeroth.project.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    private UserRepository userRepository;  // 박승기 추가

    private AuthorityRepository authorityRepository;    // 박승기 추가


    @Autowired
    public AdminServiceImpl(SqlSession sqlSession) {

        adminRepository = sqlSession.getMapper(AdminRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);    // 박승기 추가
        authorityRepository = sqlSession.getMapper(AuthorityRepository.class);  // 박승기 추가

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
    public List<userBuyDetail> selectBuyByUser(String username) {
        List<userBuyDetail> list = adminRepository.userBuyProduct(username);
        return list;
    }

    @Override
    public List<adminReview> selectAnswerByUser(String username) {
        return adminRepository.writeReviewByUser(username);
    }

    @Override
    public List<ReviewDomain> selectNoAnswerReview() {
        return null;
    }

    @Override
    public List<AuthorityDomain> selectAuthoritiesById(Long id) {
        UserDomain user = userRepository.findById(id);  // 박승기 수정

        return authorityRepository.findByUser(user);    // 박승기 수정
    }

    public SalesChkDomain salesCHK (CardDomain card){
        SalesChkDomain schk = new SalesChkDomain();
        
        System.out.println("카드정보 : " + card);
        CardDomain payCard = adminRepository.findCard(card);
        System.out.println("결제카드정보 : " + payCard);

        schk.setChkProcess(false);
        schk.setEreMag("정상 처리되었습니다.");

        if(payCard == null) {
            schk.setEreMag("잘못된 카드정보 입니다.");
            return schk;
        } else if(payCard.getBalance() < card.getBalance()){
            schk.setEreMag("잔액이 부족합니다.");
            return schk;
        }else if (schk.getEreMag().equals("정상 처리되었습니다.")){
            Long balance = card.getBalance();
            Long payment = payCard.getBalance();
            payCard.setBalance(balance-payment);
            adminRepository.updateCard(payCard);
            schk.setChkProcess(true);
        }
        return schk;
    }

    @Override
    public List<ProductManagement> ProductManagementList() {
        return adminRepository.ProductManagementList();
    }

    @Override
    public void updateLogDate(UserDomain userDomain) {
        userRepository.updateLogTime(userDomain);
    }

    @Override
    public int answerReview(Long id, String reply) {
        return adminRepository.AnswerReview(id, reply);
    }
}
