package com.azeroth.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesInfo {

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

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    LocalDateTime regdate;

    UserDomain user;   // 유저(FK)
    Long total;
    String p_name;
}



