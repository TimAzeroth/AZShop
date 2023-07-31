package com.azeroth.project.controller;

import com.azeroth.project.domain.*;
import com.azeroth.project.service.*;
import com.azeroth.project.util.U;
import com.azeroth.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<CartData> shoppingList = cartService.getCart(user.getId());
        List<AddressDomain> add = addressService.addfind(user.getId());
        if (add != null) {
            model.addAttribute("add", add);
        }

        model.addAttribute("total", total);

        model.addAttribute("u_username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("id", user.getId());
        model.addAttribute("shoppingList", shoppingList);


        ArrayList cateData = cateLoad();
        model.addAttribute("mainCategories", cateData.get(0));
        model.addAttribute("subCategories", cateData.get(1));
        model.addAttribute("categories", cateData.get(2));
        model.addAttribute("cartProducts", cateData.get(3));
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

        System.out.println(card);

        SalesChkDomain schk = adminService.salesCHK(card);
        List<CartData> shoppingList = cartService.getCart(user.getId());

        model.addAttribute("salesChk", schk.getEreMag());

        // 장바구니 목록에 상품들 가져오는 코드
        if (schk.getChkProcess()) {
            int t = 0;
            for (int i=0; i < shoppingList.size() ; i++ ){
                CartData cd = shoppingList.get(i);
                salesDomain.setId(cd.getId());
                salesDomain.setP_id(cd.getProduct_id());
                salesDomain.setAmount(cd.getAmount());
                t += salesService.insert(salesDomain);
            }
            model.addAttribute("sales", t);
        }
        ArrayList cateData = cateLoad();
        model.addAttribute("mainCategories", cateData.get(0));
        model.addAttribute("subCategories", cateData.get(1));
        model.addAttribute("categories", cateData.get(2));
        model.addAttribute("cartProducts", cateData.get(3));
        model.addAttribute("username", user.getUsername());
        return "siteSales/salesOk";
    }

    // 결제완료 알림 페이지
    @GetMapping("/salesComplete/{username}")
    public String salesComplete(Model model, @PathVariable String username) {
        UserDomain user = Util.getLoggedUser();
        List<CartData> shoppingList = cartService.getCart(user.getId());
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println(shoppingList.size());
        List<SalesInfo> sinfo = salesService.loadSalesed(username, shoppingList.size());
        int total_b = 0;
        int total_p = 0;
        for (int i=0; i <sinfo.size(); i++){
            total_p = i;
            total_b += sinfo.get(i).getTotal();
        }
        total_b += 3000;
        String tname = sinfo.get(0).getP_name();

        if(total_p == 0) {
            sinfo.get(0).setP_name(tname);
        } else {
            sinfo.get(0).setP_name(tname +" 외" + total_p +"건");
        }

        sinfo.get(0).setTotal((long) total_b);
        ArrayList cateData = cateLoad();
        model.addAttribute("mainCategories", cateData.get(0));
        model.addAttribute("subCategories", cateData.get(1));
        model.addAttribute("categories", cateData.get(2));
        model.addAttribute("cartProducts", cateData.get(3));
        model.addAttribute("SalesInfo",sinfo.get(0));
        return "/siteSales/salesComplete";
    }


    // 상단 카테고리바 추가
    public ArrayList cateLoad(){
        ArrayList<Object> cateData = new ArrayList<>();
        UserDomain loginUser = U.getLoggedUser();
        List<CategoryDomain> mainCategories = categoryService.findAllMain();
        List<CategoryDomain> subCategories = categoryService.findAllSub();
        List<CategoryDomain> categories = categoryService.findAll();
        List<CartData> cartProducts = new ArrayList<>();
        if (loginUser != null) {
            cartProducts = cartService.getCart(loginUser.getId());
        }
        cateData.add(mainCategories);
        cateData.add(subCategories);
        cateData.add(categories);
        cateData.add(cartProducts);

        return cateData;

    }

}
