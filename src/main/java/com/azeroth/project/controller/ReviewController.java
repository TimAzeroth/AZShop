package com.azeroth.project.controller;


import com.azeroth.project.domain.ReviewDomain;
import com.azeroth.project.repository.ReviewRepository;
import com.azeroth.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;


@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    public ReviewController() {
        System.out.println(getClass().getName() + "()생성");
    }


//    @PostMapping()
//    public ReviewDomain createReview(ReviewDomain reviewDomain){
//
//        return reviewService.saveReview(reviewDomain);
//    }

    @GetMapping
    public String reviews(){
        return "review/review";
    }



}
