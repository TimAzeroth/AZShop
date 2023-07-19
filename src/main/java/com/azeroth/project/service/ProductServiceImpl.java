package com.azeroth.project.service;

import com.azeroth.project.domain.ProductDomain;
import com.azeroth.project.repository.ProductRepository;
import com.azeroth.project.util.U;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
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
    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;
    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;

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
    public List<ProductDomain> listByPagination(Integer page, Model model) {
        if(page == null) page = 1;  // 디폴트는 1 page
        if(page < 1) page = 1;

        HttpSession session = U.getSession();
        Integer writePages = (Integer)session.getAttribute("writePages");
        if(writePages == null) writePages = WRITE_PAGES;  // session 에 저장된 값이 없으면 기본값으로 동작
        Integer pageRows = (Integer)session.getAttribute("pageRows");
        if(pageRows == null) pageRows = PAGE_ROWS;

        session.setAttribute("page", page);  // 현재 페이지 번호 -> session 에 저장

        long cnt = productRepository.countAll(); // 글 목록 전체의 개수
        int totalPage =  (int)Math.ceil(cnt / (double)pageRows);  // 총 몇 '페이지' 분량?

        // page 값 보정
        if(page > totalPage) page = totalPage;

        // fromRow 계산 (몇번째 데이터부터?)
        int fromRow = (page - 1) * pageRows;

        // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지' 계산
        int startPage = (((page - 1) / writePages) * writePages) + 1;
        int endPage = startPage + writePages - 1;
        if (endPage >= totalPage) endPage = totalPage;

        model.addAttribute("cnt", cnt);  // 전체 글 개수
        model.addAttribute("page", page); // 현재 페이지
        model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
        model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수
        // [페이징]
        model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
        model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
        model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
        model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지

        System.out.println(fromRow);
        System.out.println(pageRows);
        // 해당 페이지의 글 목록 읽어오기
        List<ProductDomain> list = productRepository.selectFromRow(fromRow, pageRows);
        model.addAttribute("list", list);

        return list;
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
