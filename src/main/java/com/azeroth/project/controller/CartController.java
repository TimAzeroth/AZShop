package com.azeroth.project.controller;

import com.azeroth.project.domain.CartDomain;
import com.azeroth.project.service.UserService;
import com.azeroth.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class CartController {

    @Autowired
    private UserService userService;

    public CartController(){
        System.out.println(getClass().getName() + "() 생성");
    }

    @GetMapping("/cartList")
    public void cartList(){

    }

}
