package com.azeroth.project.service;

import com.azeroth.project.domain.ReviewDomain;
import com.azeroth.project.repository.ReviewRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Value("${app.pagination.write_ADMIN}")
    private int pagination_wirte;


    @Value("${app.pagination.page_pagination}")
    private  int pagination_page;


    private ReviewRepository reviewRepository;
    @Autowired
    public ReviewServiceImpl(SqlSession sqlSession) {
        reviewRepository=sqlSession.getMapper(ReviewRepository.class);
    }
    @Override
    public int saveReview(ReviewDomain reviewDomain) {
       return reviewRepository.insert(reviewDomain);
    }

    @Override
    public List<ReviewDomain> findByProductId(Integer page, Long productId, Model model) {

            // 현재 페이지 parameter
            if(page == null) page = 1;  // 디폴트는 1 page
            if(page < 1) page = 1;

            // 페이징
            // writePages: 한 [페이징] 당 몇개의 페이지가 표시되나
            // pageRows: 한 '페이지'에 몇개의 글을 리스트 할것인가?
            HttpSession session = U.getSession();
            Integer writePages = (Integer)session.getAttribute("writePages");
            if(writePages == null) writePages = WRITE_PAGES;  // session 에 저장된 값이 없으면 기본값으로 동작
            Integer pageRows = (Integer)session.getAttribute("pageRows");
            if(pageRows == null) pageRows = PAGE_ROWS;

            session.setAttribute("page", page);  // 현재 페이지 번호 -> session 에 저장

            long cnt = postRepository.countAll(); // 글 목록 전체의 개수
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

            // 해당 페이지의 글 목록 읽어오기
            List<ReviewDomain> list = reviewRepository.paginationPage(fromRow, pageRows);
            model.addAttribute("list", list);

            return list;
        }

        return reviewRepository.findByProductId(productId);


    @Override
    public int deleteByReviewsId(Long reviewId) {
       return reviewRepository.deleteByReviewId(reviewId);

    }

    @Override
    public int replyToReview(long reply) {
         return reviewRepository.replyToReview(reply);
    }   // 어드민 응답


}
