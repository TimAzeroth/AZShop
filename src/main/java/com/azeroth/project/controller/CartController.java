package com.azeroth.project.controller;

import com.azeroth.project.domain.*;
import com.azeroth.project.service.CartService;
import com.azeroth.project.service.CategoryService;
import com.azeroth.project.service.ProductService;
import com.azeroth.project.util.U;
import com.azeroth.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @PostMapping("/cart/add/{addcode}")
    public String addOk(@RequestParam("product_id") Long product_id, @RequestParam("amount") Long amount, CartDomain cart, Model model, @PathVariable int addcode) {
        UserDomain user = U.getLoggedUser();
        ProductDomain productDomain = productService.findById(product_id);
        int result = 0;
        Long inCart = 0L;
        Long max = productDomain.getStock();
        if(cartService.getAmount(user.getId(), product_id) != null) {
            inCart = cartService.getAmount(user.getId(), product_id);
        }
        if (inCart != 0) {
            if ((amount + inCart) > max) {
                amount = max;
            } else {
                amount = amount + inCart;
            }
            result = cartService.modifyAmount(user.getId(), product_id, amount);
        } else {
            cart.setProduct_id(product_id);
            cart.setUser_id(user.getId());
            cart.setAmount(amount);
            result = cartService.addCart(cart);
        }
        model.addAttribute("result", result);
        ArrayList cateData = cateLoad();
        model.addAttribute("mainCategories", cateData.get(0));
        model.addAttribute("subCategories", cateData.get(1));
        model.addAttribute("categories", cateData.get(2));
        model.addAttribute("cartProducts", cateData.get(3));

        if (addcode == 1) {
            return "cart/addOk";
        }
        else {
            return "cart/addOk_1";
        }
    }

    @PostMapping("/cart/update")
    @ResponseBody
    public int updateOk(@RequestParam("product_id") Long product_id, @RequestParam("amount") Long amount) {
        UserDomain user = U.getLoggedUser();

        return cartService.modifyAmount(user.getId(), product_id, amount);
    }

    @PostMapping("/cart/delete")
    public String deleteOk(Long id, Model model) {
        cartService.deleteCart(id, null);
        ArrayList cateData = cateLoad();
        model.addAttribute("mainCategories", cateData.get(0));
        model.addAttribute("subCategories", cateData.get(1));
        model.addAttribute("categories", cateData.get(2));
        model.addAttribute("cartProducts", cateData.get(3));
        return "redirect:/user/cart";
    }

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
