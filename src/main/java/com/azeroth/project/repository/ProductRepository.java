package com.azeroth.project.repository;

import com.azeroth.project.domain.ProductDomain;

import java.util.List;

public interface ProductRepository {

    // 상품 리스트 조회 ( 카테고리별 )
    List<ProductDomain> findByCate(String cate);

    // 상품 리스트 조회 ( 검색어로 검색 )
    List<ProductDomain> findBySearch(String Search);

    // 메인 페이지에 상품 보이기 (rank 적용)
    List<ProductDomain> listByPriority();

    // 카테고리에 있는 상품 조회 (rank 적용)
    List<ProductDomain> listByCategory(String maincode, String subcode);

    // 특정 상품 조회 ( 상세페이지 )
    ProductDomain findById(Long id);

    // 상품 추가
    int addProduct(ProductDomain product);

    // 상품 수정
    int update(ProductDomain product);

    // 상품 삭제
    int delete(ProductDomain product);

}
