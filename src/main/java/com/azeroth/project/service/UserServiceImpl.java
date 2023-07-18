package com.azeroth.project.service;

import com.azeroth.project.domain.AuthorityDomain;
import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.repository.AuthorityRepository;
import com.azeroth.project.repository.UserRepository;
import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(SqlSession sqlSession){
        userRepository = sqlSession.getMapper(UserRepository.class);
        authorityRepository = sqlSession.getMapper(AuthorityRepository.class);
        System.out.println(getClass().getName() + "() 생성");
    }

    @Override
    public UserDomain findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isExist(String username) {
        UserDomain user = findByUsername(username);
        return (user != null) ? true : false;
    }

    @Override
    public int register(UserDomain user) {
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());   // password 는 암호화 해서 저장 (추후 인코딩 설정)
        AuthorityDomain auth = authorityRepository.findByName("ROLE_MEMBER");
        user.setAuthority_id(auth.getId());

        userRepository.insert(user);  // 새로이 회원(User) 저장, id값 받아옴

        return 1;
    }
}
