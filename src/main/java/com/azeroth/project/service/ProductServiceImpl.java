package com.azeroth.project.service;

import com.azeroth.project.domain.ProductDomain;
import com.azeroth.project.repository.ProductRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${app.upload.path}")
    private String uploadDir;

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(SqlSession sqlSession) {
        productRepository = sqlSession.getMapper(ProductRepository.class);
    }

    @Override
    public int addProduct(ProductDomain productDomain, MultipartFile file) {
        return upload(productDomain, file);
    }

    @Override
    public List<ProductDomain> listByPriority() {
        return productRepository.listByPriority();
    }

    @Override
    public List<ProductDomain> listByCategory(String maincode, String subcode) {
        return productRepository.listByCategory(maincode, subcode);
    }

    private int upload(ProductDomain productDomain, MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null || originalFilename.length() == 0) {
            productDomain.setP_img(null);
            return productRepository.addProduct(productDomain);
        }
        // 원본파일명
        String sourceName = StringUtils.cleanPath(originalFilename);

        // 저장될 파일명
        String fileName = sourceName;

        // 파일이 중복되는지 확인
        File file = new File(uploadDir + File.separator + sourceName);
        if (file.exists()) {
            int pos = fileName.lastIndexOf(".");
            if (pos > -1) {
                String name = fileName.substring(0, pos);
                String ext = fileName.substring(pos + 1);

                fileName = name + "_" + System.currentTimeMillis() + "." + ext;
            } else {
                fileName += "_" + System.currentTimeMillis();
            }
        }

        productDomain.setP_img(fileName);

        // java.nio.*
        Path copyOfLocation = Paths.get(new File(uploadDir + File.separator + fileName).getAbsolutePath());

        try {
            Files.copy(
                    multipartFile.getInputStream(),
                    copyOfLocation,
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productRepository.addProduct(productDomain);
    }
}
