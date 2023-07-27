package com.azeroth.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartData {
    private Long id;
    private Long amount;
    private Long product_id;
    private Long price;
    private Long stock;
    private String p_img;
    private String p_name;

}
