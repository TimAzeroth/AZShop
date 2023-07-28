package com.azeroth.project.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductManagement {

    Long id;            //PK
    String p_name;      // 상품명
    String main_cate;   // 메인 카테고리
    String sub_cate;    // 서브 카테고리
    String p_img;       // 상품이미지
    String detail;      // 상품상세설명
    Long price;         // 가격
    Long stock;         // 재고
    Long p_rank;        // 판매량
    String mainname;    // 메인카테고리 이름
    String subname;     // 서브카테고리 이름
}
