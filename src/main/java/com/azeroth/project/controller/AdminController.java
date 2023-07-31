package com.azeroth.project.controller;


import com.azeroth.project.domain.*;
import com.azeroth.project.service.AdminService;
import com.azeroth.project.service.CartService;
import com.azeroth.project.service.CategoryService;
import com.azeroth.project.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;


    // 고객 리스트 호출 페이지
    @GetMapping("/searchuser")
    public void searchUser(Model model) {
        ArrayList cateData = cateLoad();
        List<UserDomain> userList = adminService.findByAllUser();

        for (UserDomain user : userList) {
            String phon = user.getPhone();
            String phon_st = phon.substring(0,3);
            String phon_mid = "-****-";
            String phon_ed = phon.substring(7,11);
            user.setPhone(phon_st+phon_mid+phon_ed);
        }

        model.addAttribute("userList", userList);
        model.addAttribute("mainCategories", cateData.get(0));
        model.addAttribute("subCategories", cateData.get(1));
        model.addAttribute("categories", cateData.get(2));
        model.addAttribute("cartProducts", cateData.get(3));
    }

    //selectBuyByUser 특정 회원이 구매한 구매목록
    @GetMapping("/BuyByUser/{username}")
    public String BuyByUser (Model model, @PathVariable String username){
        ArrayList cateData = cateLoad();
        model.addAttribute("mainCategories", cateData.get(0));
        model.addAttribute("subCategories", cateData.get(1));
        model.addAttribute("categories", cateData.get(2));
        model.addAttribute("cartProducts", cateData.get(3));

        model.addAttribute("userBuyList", adminService.selectBuyByUser(username));

        return "/admin/BuyByUser";
    }

    @GetMapping("/userDetail/{username}")
    public String userDetail(Model model, @PathVariable String username){


        UserDomain user = adminService.findByUsername(username);
        model.addAttribute("user_id", user.getId());
        model.addAttribute("username", username);
        model.addAttribute("profileimg",user.getProfileimg());
        model.addAttribute("nickname",user.getNickname());
        model.addAttribute("email",user.getEmail());
        model.addAttribute("u_status",user.getU_status());
        model.addAttribute("phone",user.getPhone());
        ArrayList cateData = cateLoad();
        model.addAttribute("mainCategories", cateData.get(0));
        model.addAttribute("subCategories", cateData.get(1));
        model.addAttribute("categories", cateData.get(2));
        model.addAttribute("cartProducts", cateData.get(3));
//        model.addAttribute("userInfo",user);

        //model.addAttribute("categories", categories);
        return "/admin/userDetail";
    }

    @GetMapping("/productmanagement")
    public void productManagement(Model model){

        model.addAttribute("productList", adminService.ProductManagementList());
        ArrayList cateData = cateLoad();
        model.addAttribute("mainCategories", cateData.get(0));
        model.addAttribute("subCategories", cateData.get(1));
        model.addAttribute("categories", cateData.get(2));
        model.addAttribute("cartProducts", cateData.get(3));
    }

    @GetMapping("/answerByUser/{username}")
    public String answerByUser(Model model, @PathVariable String username){
        List<adminReview> adminReviews = adminService.selectAnswerByUser(username);
        model.addAttribute("anserList", adminReviews);


        ArrayList cateData = cateLoad();
        model.addAttribute("mainCategories", cateData.get(0));
        model.addAttribute("subCategories", cateData.get(1));
        model.addAttribute("categories", cateData.get(2));
        model.addAttribute("cartProducts", cateData.get(3));
        return "/admin/answerByUser";
    }

    @PostMapping("/insertAnswer")
    @ResponseBody
    public List<adminReview> insertAnswer (@RequestParam("id") Long id,
                                   @RequestParam("reply") String reply,
                                   @RequestParam("username") String username){

        adminService.answerReview(id, reply);

        return adminService.selectAnswerByUser(username);
    }

    @GetMapping("/usercart/{user_id}")
    public String usercart (Model model, @PathVariable Long user_id){
        System.out.println("user_id : " + user_id);
        List<CartData> cartProducts = cartService.getCart(user_id);
        model.addAttribute("userCart", cartProducts);
        ArrayList cateData = cateLoad();
        model.addAttribute("mainCategories", cateData.get(0));
        model.addAttribute("subCategories", cateData.get(1));
        model.addAttribute("categories", cateData.get(2));
        model.addAttribute("cartProducts", cateData.get(3));
        return "/admin/usercart";
    }

    @PostMapping("/changPrank")
    @ResponseBody
    public void changPrank(@RequestParam("Product_id") Long Product_id,
                           @RequestParam("p_rank") Long prank){
        adminService.pRankUpdate(Product_id, prank);
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



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new ProductValidator());
    }
}
