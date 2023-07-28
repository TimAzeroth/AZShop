package com.azeroth.project.controller;

import com.azeroth.project.domain.QryResult;
import com.azeroth.project.domain.QryReviewList;
import com.azeroth.project.domain.ReviewDomain;
import com.azeroth.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    public ReviewController () {
        System.out.println(getClass().getName() + "()생성");
    }

    @GetMapping("/list")
    public QryReviewList list(Long product_id) {
        return reviewService.list(product_id);
    }

    @PostMapping("/save")
    public QryResult save(@RequestParam("user_id") Long user_id,
                          @RequestParam("product_id") Long product_id,
                          String content) {
        return reviewService.save(user_id, product_id, content);
    }

    @PostMapping("/delete")
    public QryResult delete(Long id) {
        return reviewService.delete(id);
    }





}
