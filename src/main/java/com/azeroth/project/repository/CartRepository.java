package com.azeroth.project.repository;

import com.azeroth.project.domain.CartData;
import com.azeroth.project.domain.CartDomain;

import java.util.List;

public interface CartRepository {
    int addCart(CartDomain cart);

    // user_id로 장바구니 상품들 불러오기
    List<CartData> getCartData(Long user_id);

    // 삭제
    int deleteCart(Long id);
}
