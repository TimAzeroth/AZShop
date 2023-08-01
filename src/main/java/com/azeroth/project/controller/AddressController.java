package com.azeroth.project.controller;

import com.azeroth.project.domain.*;
import com.azeroth.project.service.CartService;
import com.azeroth.project.service.CategoryService;
import com.azeroth.project.service.UserService;
import com.azeroth.project.util.U;
import com.azeroth.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class AddressController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartService cartService;

    public AddressController(){}


    @GetMapping("/addressManage")
    public void addressManage(AddressDomain addressDomain,
                              Model model
    ){
        UserDomain user = Util.getLoggedUser();

        List<AddressDomain> list = userService.findAddressByUserId(user.getId());

        model.addAttribute("list",list);

        ArrayList cateData = cateLoad();
        model.addAttribute("mainCategories", cateData.get(0));
        model.addAttribute("subCategories", cateData.get(1));
        model.addAttribute("categories", cateData.get(2));
        model.addAttribute("cartProducts", cateData.get(3));
    }

    @GetMapping("/changeAddress")
    public void changeAddress(Model model){
        ArrayList cateData = cateLoad();
        model.addAttribute("mainCategories", cateData.get(0));
        model.addAttribute("subCategories", cateData.get(1));
        model.addAttribute("categories", cateData.get(2));
        model.addAttribute("cartProducts", cateData.get(3));
    }

    @PostMapping("/changeAddress")
    public String changeAddress1(AddressDomain addressDomain,
                                 Model model
    ){
        UserDomain user = Util.getLoggedUser();
        Long user_id = user.getId();

        addressDomain.setUser_id(user_id);

        int cnt = userService.saveAddress(addressDomain);

        model.addAttribute("result",cnt);

        return "/user/changeAddressOk";
    }

    @PostMapping("/deleteAddress")
    public String deleteAddress(AddressDomain addressDomain,
                                Model model
    ){
        System.out.println(addressDomain);
        addressDomain = userService.findAddressById(addressDomain.getId());
        System.out.println(addressDomain.getId());
        System.out.println(addressDomain.getAddress());
        int cnt = userService.deleteAddress(addressDomain);

        model.addAttribute("result",cnt);

        System.out.println(cnt);

        return "/user/deleteAddressOk";
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
