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

import java.util.List;

@Controller
@RequestMapping("/siteSales")
public class SalesController {

    @Autowired
    private UserService userService;

    @Autowired
    private SalesService salesService;

    @GetMapping("/sales")
    public String sales(
            String u_username, Model model)
    {

        // 세션 정보 땜빵용
        UserDomain user = new UserDomain();
        user.setId(1L);
        user.setAuthority_id(1L);
        user.setUsername("user1");
        user.setNickname("사용자1");
        user.setEmail("user1@gmail.com");
        user.setProfileimg("face01.png");
        user.setU_status("USE");
        user.setPhone("01012341234");

        model.addAttribute("u_username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("nickname", user.getNickname());

        AddressDomain add = new AddressDomain();
        add.setId(1L);
        add.setUser_id(1L);
        add.setAddress("서울시 종로구 테헤란로 56길");
        add.setAddress_detail("234-5번지");
        add.setAddress_name("유저1집");
        add.setPostcode("12345");

        model.addAttribute("address_name", add.getAddress_name());
        model.addAttribute("address_detail",add.getAddress_detail());
        model.addAttribute("email",user.getEmail());
        model.addAttribute("phone",user.getPhone());


        System.out.println("실행 확인용");
        return "/siteSales/sales";
    }


    @PostMapping("/sales")
    public String salesPosting(
            @ModelAttribute("salesdomain")
            @Valid
            SalesDomain salesDomain,
            Model model
    ){
        // SalesChkDomain chk = adminService.salesCHK();  // 카드 도메인 넘기기
        if (true) {
            int insert = salesService.insert(salesDomain);
            model.addAttribute("result", insert);


            return "redirect:/siteSales/sales";
        }

        return "siteSales/salesOk";
    }


    // 결제완료 알림 페이지
    @GetMapping("/salesComplete")
    public String salesComplete(){
        return "/siteSales/salesComplete";
    }


}
