package com.azeroth.project.controller;

import com.azeroth.project.domain.CardDomain;
import com.azeroth.project.domain.SalesChkDomain;
import com.azeroth.project.domain.SalesDomain;
import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.repository.SalesRepository;
import com.azeroth.project.repository.UserRepository;
import com.azeroth.project.service.AdminService;
import com.azeroth.project.service.SalesService;
import jakarta.validation.Valid;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/siteSales")
public class SalesController {

    @Autowired
    private SalesService salesService;


    @GetMapping("/sales")
    public String sales(
           // @PathVariable String username, Model model
    ) {
       // model.addAttribute("sales", salesService.saleslist(username));
        System.out.println("실행 확인용");
        return "siteSales/sales";
    }


    @PostMapping("/sales")
    public String salesPosting(
            @ModelAttribute("sales")
            @Valid
            SalesDomain salesDomain,
            Model model
    ){
        // SalesChkDomain chk = adminService.salesCHK();  // 카드 도메인 넘기기
        if (true) {
            int insert = salesService.insert(salesDomain);
            model.addAttribute("result", insert);

            return "siteSales/salesOk";
        }

        return "redirect:/siteSales/sales";
    }

    // 결제완료 알림 페이지
    @GetMapping("/salesComplete")
    public String salesComplete(){
        return "/siteSales/salesComplete";
    }


}
