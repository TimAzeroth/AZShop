package com.azeroth.project.controller;

import com.azeroth.project.domain.*;
import com.azeroth.project.service.*;
import com.azeroth.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/sales")
    public String sales(UserDomain user,
                        @RequestParam Integer total,
                        Model model) {
        // 세션에 저장된 사용자의 정보
        user = Util.getLoggedUser();

        // 카테고리 헤더 부분 내용
//        List<CartData> cartlist = new ArrayList<>();
//        cartlist= cartService.getCart(user.getId());
//        List<CategoryDomain> mainCategories = categoryService.findAllMain();
//        List<CategoryDomain> subCategories = categoryService.findAllSub();
//        List<CategoryDomain> categories = categoryService.findAll();
//
//        model.addAttribute("mainCategories", mainCategories);
//        model.addAttribute("subCategories", subCategories);
//        model.addAttribute("categories", categories);
//        model.addAttribute("cartProducts", cartlist);

        model.addAttribute("total", total);

        model.addAttribute("u_username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("nickname", user.getNickname());

        model.addAttribute("id", user.getId());
        System.out.println("-------------------------------------------------");

        System.out.println(addressService.addfind(user.getId()));

        List<AddressDomain> add = addressService.addfind(user.getId());
        if (add != null) {
            model.addAttribute("add", add);
        }

        System.out.println("실행 확인용");
        return "siteSales/sales";
    }


    @PostMapping("/sales")
    public String salesOk(
            SalesDomain salesDomain,
            CardDomain card,
            Model model
    ) {
        UserDomain user = Util.getLoggedUser();
        salesDomain.setId(user.getId());
        SalesChkDomain schk = adminService.salesCHK(card);
        System.out.println("카드정보 : " + card);
        model.addAttribute("card", schk);

        if (schk.getChkProcess()) {
            int sales = salesService.insert(salesDomain);
            model.addAttribute("sales", sales);
        }

        return "siteSales/salesOk";
    }

    // 결제완료 알림 페이지
    @GetMapping("/salesComplete")
    public String salesComplete() {
        return "/siteSales/salesComplete";
    }


}
