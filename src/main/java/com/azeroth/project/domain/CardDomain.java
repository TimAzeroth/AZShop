package com.azeroth.project.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDomain {
    Long id;                // PK
    String c_num;           // 카드번호
    String c_year;          // 카드유효기간(년)
    String c_month;         // 카드유효기간(월)
    String c_cvc;           // 카드 CVC
    Long balance;           // 카드잔액
}
