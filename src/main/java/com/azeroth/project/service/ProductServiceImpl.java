package com.azeroth.project.service;

import com.azeroth.project.domain.ProductDomain;
import com.azeroth.project.repository.ProductRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(SqlSession sqlSession) {
        productRepository = sqlSession.getMapper(ProductRepository.class);
    }

    @Override
    public int addProduct(ProductDomain productDomain) {
        return productRepository.addProduct(productDomain);
    }

    @Override
    public int addProduct(ProductDomain productDomain, Map<String, MultipartFile> files) {
        int result = productRepository.addProduct(productDomain);
        return result;
    }
}
