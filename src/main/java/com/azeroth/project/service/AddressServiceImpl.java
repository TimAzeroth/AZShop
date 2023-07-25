package com.azeroth.project.service;

import com.azeroth.project.domain.AddressDomain;
import com.azeroth.project.repository.AddressRepository;
import com.azeroth.project.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    private UserRepository userRepository;

    @Autowired
    public AddressServiceImpl(SqlSession sqlSession) {
        addressRepository = sqlSession.getMapper(AddressRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);
        System.out.println(getClass().getName() + "() 생성");
    }
    @Override
    public List<AddressDomain> addfind(Long id) {
        return addressRepository.findByAdd(id);
    }






}
