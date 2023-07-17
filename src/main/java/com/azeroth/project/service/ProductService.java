package com.azeroth.project.service;

import com.azeroth.project.domain.ProductDomain;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ProductService {
    // 상품 등록
    int addProduct(ProductDomain productDomain);

    // 상품 등록 (첨부 이미지 없을 경우)
    int addProduct(ProductDomain productDomain, Map<String, MultipartFile> files);
}
