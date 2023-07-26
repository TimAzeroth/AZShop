package com.azeroth.project.controller;

import com.azeroth.project.domain.*;
import com.azeroth.project.service.*;
import com.azeroth.project.util.Util;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/siteSales")
public class SalesController {

    @Autowired
    private UserService userService;

    @Autowired
    private SalesService salesService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/sales")
    public String sales(
            String u_username, Model model)
    {

        // 세션에 저장된 사용자의 정보
        UserDomain user = Util.getLoggedUser();

        model.addAttribute("u_username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("id", user.getId());
        System.out.println("-------------------------------------------------");

        System.out.println( addressService.addfind(user.getId()));

        List<AddressDomain> add =  addressService.addfind(user.getId());
        if (add != null ){
            model.addAttribute("add", add);
        }

        System.out.println("실행 확인용");
        return "/siteSales/sales";
    }


    @PostMapping("/sales")
    public String salesOk(
            @RequestParam
            @ModelAttribute("sales")
            @Valid
            SalesDomain salesDomain,
            Model model
    ){

        CartData data = new CartData();
        data.setAmount(1l);
        salesDomain.setP_id(1l);


        int insert = salesService.insert(salesDomain);
        model.addAttribute("result", insert);

        return "/siteSales/salesOk";
    }


    // 결제완료 알림 페이지
    @GetMapping("/salesComplete")
    public String salesComplete(){
        return "/siteSales/salesComplete";
    }


}
