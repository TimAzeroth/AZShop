package com.azeroth.project.service;

import com.azeroth.project.domain.AuthorityDomain;
import com.azeroth.project.domain.ReviewDomain;
import com.azeroth.project.domain.SalesDomain;
import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.repository.AdminRepository;
import com.azeroth.project.repository.CategoryRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

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
        return null;
    }

    @Override
    public UserDomain findByUsername(String username) {
        return null;
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
}
