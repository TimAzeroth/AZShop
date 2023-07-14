package com.azeroth.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDomain {

    Long id;                // PK
    Long user_id;           // FK
    String address_name;    // 주소명
    String address;         // 주소
    String address_detail;  // 상세주소
    String postcode;        // 우편번호
}
