package com.azeroth.project.controller;


import com.azeroth.project.domain.ReviewDomain;
import com.azeroth.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    public ReviewController() {
        System.out.println(getClass().getName() + "()생성");
    }


    @PostMapping()
    public ReviewDomain createReview(ReviewDomain reviewDomain){

        return reviewService.saveReview(reviewDomain);
    }

    @GetMapping
    public String reviews(Long productId, Model model){
        List<ReviewDomain> reviews=reviewService.findByProductId(productId);
        model.addAttribute("reviews",reviews);
        return "review/review";
    }

    @PostMapping("/List")
    public String getReview(Long productId, Model model){
        List<ReviewDomain> reviews = reviewService.findByProductId(productId);
        model.addAttribute("reviews",reviews);
        return "review/review";
    }
    @PostMapping("/delete")
    public String deleteByReviewId(@PathVariable  Long reviewId, Model model){
        int result = reviewService.deleteByReviewsId(reviewId);
        model.addAttribute("result", result);
        return "/deleteOk";

    }


}
