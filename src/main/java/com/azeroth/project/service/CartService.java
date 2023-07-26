package com.azeroth.project.service;

import com.azeroth.project.domain.CartData;
import com.azeroth.project.domain.CartDomain;

import java.util.List;

public interface CartService {
    int addCart(CartDomain cart);

    List<CartData> getCartData(Long user_id);
}
