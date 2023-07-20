package com.azeroth.project.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDomain.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDomain product = (ProductDomain) target;
        String p_name = product.getP_name();
        String main_cate = product.getMain_cate();
        String sub_cate = product.getSub_cate();
        Long price = product.getPrice();

        // 입력하지 않았는지 확인
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "p_name", "상품명을 입력해주세요");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "main_cate", "대분류를 선택해주세요");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sub_cate", "소분류를 선택해주세요");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "가격을 입력해주세요");
    }
}
