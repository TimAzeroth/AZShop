package com.azeroth.project.controller;

import com.azeroth.project.domain.CardDomain;
import com.azeroth.project.domain.SalesChkDomain;
import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.repository.SalesRepository;
import com.azeroth.project.repository.UserRepository;
import com.azeroth.project.service.AdminService;
import com.azeroth.project.service.SalesService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/siteSales")
public class SalesController {


    @GetMapping("/sales")
    public String sales() {
        System.out.println("실행 확인용");
        return "/siteSales/sales";
    }


    @PostMapping("/sales")
    public String salesPosting(){
        // SalesChkDomain chk = adminService.salesCHK();  // 카드 도메인 넘기기
        return "";
    }

    // 결제완료 알림 페이지
    @GetMapping("/salesComplete")
    public String salesComplete(){
        return "/siteSales/salesComplete";
    }


}
