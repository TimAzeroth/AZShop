package com.azeroth.project.repository;

import com.azeroth.project.domain.CartDomain;
import com.azeroth.project.domain.ProductDomain;

import java.util.List;

public interface CartRepository {

    // Cart 추가
    int insert(CartDomain cartDomain);

    // 사용자 FK 값을 이용하여 Cart 검색
    CartDomain findByUserId(Long id);

    // 장바구니 PK 값을 이용하여 Cart 검색
    CartDomain findById(Long id);

    // 특정 Cart 의 상품목록 불러오기
    List<ProductDomain> findByCart(CartDomain cartDomain);

    // 특정 Cart 에 상품등록하기
    int addProductToCart(Long cart_id, Long product_id);

    // 특정 Cart 의 상태, 수량 업데이트하기
    int updateCart(CartDomain cartDomain);

    // 특정 Cart 의 특정 Product 삭제하기
    int deleteProductFromCart(Long cart_id, Long product_id);


}
