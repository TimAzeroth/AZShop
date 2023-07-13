package com.azeroth.project.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesDomain {

    Long id;
    String u_username;
    Long p_id;
    Long amount;
    String address;
    String address_detail;
    String postcode;
    // 배송 요청사항
    String deliveryreq;
    // 송장번호
    String tracknum;
}
