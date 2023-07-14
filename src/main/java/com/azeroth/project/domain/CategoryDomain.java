package com.azeroth.project.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDomain {
    String maincode;        // 메인카테고리 코드
    String subcode;         // 서브카테고리 코드
    String mainname;        // 메인카테고리 이름
    String subname;         // 서브카테고리 이름
}
