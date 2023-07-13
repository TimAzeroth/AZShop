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

    Long id;
    Long user_id;
    String address_name;
    String address;
    String address_detail;
    String postcode;
}
