package com.azeroth.project.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class userBuyDetail {

    Long product_id;
    String product_img;
    String product_name;
    Long product_price;
    Long product_stork;
    String user_account;
    String user_name;
    Long order_stork;
}
