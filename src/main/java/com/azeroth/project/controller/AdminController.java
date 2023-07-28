package com.azeroth.project.controller;


import com.azeroth.project.domain.ProductValidator;
import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    // 고객 리스트 호출 페이지
    @GetMapping("/searchuser")
    public void searchUser(Model model) {
        List<UserDomain> userList = adminService.findByAllUser();

        for (UserDomain user : userList) {
            String phon = user.getPhone();
            String phon_st = phon.substring(0,3);
            String phon_mid = "-****-";
            String phon_ed = phon.substring(7,11);
            user.setPhone(phon_st+phon_mid+phon_ed);
        }

        model.addAttribute("userList", userList);
    }

    //selectBuyByUser 특정 회원이 구매한 구매목록
    @GetMapping("/BuyByUser/{username}")
    public String BuyByUser (Model model, @PathVariable String username){

        model.addAttribute("userBuyList", adminService.selectBuyByUser(username));

        return "/admin/BuyByUser";
    }

    @GetMapping("/userDetail/{username}")
    public String userDetail(Model model, @PathVariable String username){

        UserDomain user = adminService.findByUsername(username);

        model.addAttribute("profileimg",user.getProfileimg());
        model.addAttribute("nickname",user.getNickname());
        model.addAttribute("email",user.getEmail());
        model.addAttribute("u_status",user.getU_status());
        model.addAttribute("phone",user.getPhone());
//        model.addAttribute("userInfo",user);

        //model.addAttribute("categories", categories);
        return "/admin/userDetail";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new ProductValidator());
    }
}
