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
    Long id;
    String c_num;
    String c_year;
    String c_month;
    String c_cvc;
    Long balance;
}
