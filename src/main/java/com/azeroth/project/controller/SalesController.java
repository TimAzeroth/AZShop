package com.azeroth.project.controller;

import com.azeroth.project.repository.SalesRepository;
import com.azeroth.project.service.SalesService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/siteSales")
public class SalesController {

    private SalesService salesService;

    private SalesRepository salesRepository;

    // 주문 / 결제 페이지  -- 유저의 아이디를 가져오지 못하면 에러 발생하여 주석 처리
//    @GetMapping("/sales")
//    public String sales(@PathVariable String u_username, Model model) {
//        System.out.println("실행 확인용");
//        model.addAttribute("sales", salesService.saleslist(u_username));
//
//        return "/siteSales/sales";
//    }

    @GetMapping("/sales")
    public String sales() {
        System.out.println("실행 확인용");
        return "/siteSales/sales";
    }

    // 결제완료 알림 페이지

    @GetMapping("/salesComplete")
    public String salesComplete(){
        return "/siteSales/salesComplete";
    }


}
