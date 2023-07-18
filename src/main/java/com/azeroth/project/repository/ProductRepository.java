package com.azeroth.project.repository;

import com.azeroth.project.domain.ProductDomain;

import java.util.List;

public interface ProductRepository {

    // 상품 리스트 조회 ( 메인페이지 )
    List<ProductDomain> findByAll();

    // 상품 리스트 조회 ( 카테고리별 )
    List<ProductDomain> findByCate(String cate);

    // 상품 리스트 조회 ( 검색어로 검색 )
    List<ProductDomain> findBySearch(String Search);

    // 특정 상품 조회 ( 상세페이지 )
    ProductDomain findById(Long id);

    // 상품 추가
    int addProduct(ProductDomain product);

    // 상품 수정
    int update(ProductDomain product);

    // 상품삭제
    int delete(ProductDomain product);
}
