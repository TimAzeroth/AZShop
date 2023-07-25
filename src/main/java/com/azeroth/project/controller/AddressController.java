package com.azeroth.project.controller;

import com.azeroth.project.domain.AddressDomain;
import com.azeroth.project.domain.CartDomain;
import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.service.UserService;
import com.azeroth.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class AddressController {

    @Autowired
    private UserService userService;

    public AddressController(){}


    @GetMapping("/addressManage")
    public void addressManage(AddressDomain addressDomain,
                              Model model
    ){
        UserDomain user = Util.getLoggedUser();

        List<AddressDomain> list = userService.findAddressByUserId(user.getId());

        model.addAttribute("list",list);
    }

    @GetMapping("/changeAddress")
    public void changeAddress(){}

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

}
