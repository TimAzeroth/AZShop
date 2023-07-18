package com.azeroth.project.service;

import com.azeroth.project.domain.ProductDomain;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ProductService {

    // 상품 등록 (첨부 이미지 있는 경우)
    int addProduct(ProductDomain productDomain, MultipartFile files);
}
