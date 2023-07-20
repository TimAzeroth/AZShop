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

    Long id;                    // PK
    String u_username;          // 로그인 id
    Long p_id;                  // 상품 PK id
    Long amount;                // 갯수
    String address;             // 주소
    String address_detail;      // 상세주소
    String postcode;            // 우편번호

    // 배송 요청사항
    String deliveryreq;
    // 송장번호
    String tracknum;

    UserDomain user;   // 유저(FK)


}
