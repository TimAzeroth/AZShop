package com.azeroth.project.controller;

import com.azeroth.project.domain.*;
import com.azeroth.project.repository.SalesRepository;
import com.azeroth.project.repository.UserRepository;
import com.azeroth.project.service.AdminService;
import com.azeroth.project.service.SalesService;
import com.azeroth.project.service.UserService;
import jakarta.validation.Valid;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/siteSales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping("/sales")
    public String sales(
//            @PathVariable String u_username, Model model)
            )
    {
        //model.addAttribute("salesdomain", salesService.saleslist(u_username));
        System.out.println("실행 확인용");
        return "siteSales/sales";
    }

//
//    @PostMapping("/sales")
//    public String salesPosting(
//            @ModelAttribute("salesdomain")
//            @Valid
//            SalesDomain salesDomain,
//            Model model
//    ){
//        // SalesChkDomain chk = adminService.salesCHK();  // 카드 도메인 넘기기
//        if (true) {
//            int insert = salesService.insert(salesDomain);
//            model.addAttribute("result", insert);
//
//
//            return "redirect:/siteSales/sales";
//        }
//
//        return "siteSales/salesOk";
//    }


    @InitBinder  // 이 컨트롤러 클래스의 handler 에서 폼 데이터를 바인딩 할때 검증하는 Validator 객체 지정
    public void initBinder(WebDataBinder binder){
        System.out.println("initBinder() 호출");
        binder.setValidator(new UserValidator());
    }


    // 결제완료 알림 페이지
    @GetMapping("/salesComplete")
    public String salesComplete(){
        return "/siteSales/salesComplete";
    }


}
