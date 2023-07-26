package com.azeroth.project.controller;

import com.azeroth.project.domain.CartData;
import com.azeroth.project.domain.CartDomain;
import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.service.CartService;
import com.azeroth.project.service.ProductService;
import com.azeroth.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/product/addcart")
    public String addCartOk(@RequestParam("product_id") Long product_id, @RequestParam("amount") Long amount, CartDomain cart, Model model) {
        UserDomain user = Util.getLoggedUser();
        cart.setProduct_id(product_id);
        cart.setAmount(amount);
        cart.setUser_id(user.getId());
        int result = cartService.addCart(cart);
        System.out.println(result);
        model.addAttribute("result", result);
        return "product/addcartOk";
    }

    @GetMapping("/user/cartList")
    public String getList(Model model) {
        UserDomain user = Util.getLoggedUser();
        List<CartData> cartList = cartService.getCartData(user.getId());
        System.out.println(cartList);
        model.addAttribute("cartList", cartList);
        return "/user/cartList";
    }

//    @PostMapping("/user/cartDelete")
//    public String deleteOk(Long id, Model model) {
//        model.addAttribute("result", TODO);
//    }
}
