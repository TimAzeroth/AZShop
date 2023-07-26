package com.azeroth.project.service;

import com.azeroth.project.domain.CartData;
import com.azeroth.project.domain.CartDomain;
import com.azeroth.project.repository.CartRepository;
import com.azeroth.project.repository.ProductRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(SqlSession sqlSession) {
        cartRepository = sqlSession.getMapper(CartRepository.class);
    }

    @Override
    public int addCart(CartDomain cart) {
        return cartRepository.addCart(cart);
    }

    @Override
    public List<CartData> getCartData(Long user_id) {
        return cartRepository.getCartData(user_id);
    }


}
