package com.azeroth.project.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDomain {

    Long id;
    String p_name;
    String main_cate;
    String sub_cate;
    String p_img;
    String detail;
    Long price;
    Long stock;
    Long p_rank;
}
