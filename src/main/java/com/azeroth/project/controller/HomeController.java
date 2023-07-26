package com.azeroth.project.controller;

import com.azeroth.project.domain.CartData;
import com.azeroth.project.domain.CategoryDomain;
import com.azeroth.project.domain.ProductDomain;
import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.service.CartService;
import com.azeroth.project.service.CategoryService;
import com.azeroth.project.service.ProductService;
import com.azeroth.project.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @RequestMapping("/")
    public String index(Integer page, Model model) {
        UserDomain user = U.getLoggedUser();
        List<CartData> cartProducts = new ArrayList<>();
        if (user != null) {
            cartProducts = cartService.getCart(user.getId());
        }
        model.addAttribute("cartProducts", cartProducts);
        List<CategoryDomain> mainCategories = categoryService.findAllMain();
        List<CategoryDomain> subCategories = categoryService.findAllSub();
        List<CategoryDomain> categories = categoryService.findAll();
        List<ProductDomain> products = productService.listByPagination(page, model);
        model.addAttribute("products", products);
        model.addAttribute("mainCategories", mainCategories);
        model.addAttribute("subCategories", subCategories);
        model.addAttribute("categories", categories);
        return "/index";
    }

    @RequestMapping("/category")
    public void category(@RequestParam("maincode") String maincode, @RequestParam("subcode") String subcode, Model model) {
        UserDomain user = U.getLoggedUser();
        List<CartData> cartProducts = new ArrayList<>();
        if (user != null) {
            cartProducts = cartService.getCart(user.getId());
        }
        model.addAttribute("cartProducts", cartProducts);
        List<CategoryDomain> mainCategories = categoryService.findAllMain();
        List<CategoryDomain> subCategories = categoryService.findAllSub();
        List<CategoryDomain> categories = categoryService.findAll();
        List<ProductDomain> products = productService.listByCategory(maincode, subcode);
        model.addAttribute("mainCategories", mainCategories);
        model.addAttribute("subCategories", subCategories);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
    }

    @RequestMapping("/auth")
    @ResponseBody
    public Authentication auth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
