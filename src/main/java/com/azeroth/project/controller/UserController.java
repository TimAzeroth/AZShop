package com.azeroth.project.controller;

import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.domain.UserValidator;
import com.azeroth.project.service.UserService;
import com.azeroth.project.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    public UserController(){
        System.out.println(getClass().getName() + "() 생성");
    }

    @GetMapping("/login")
    public void login(){}


    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public  String registerOk(
                              @RequestParam("upfile")MultipartFile file,
                              @Valid UserDomain user,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes
    ){
        if(!result.hasFieldErrors("username") && userService.isExist(user.getUsername())){
            result.rejectValue("username","이미 존재하는 아이디입니다");
        }

        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("username",user.getUsername());
            redirectAttributes.addFlashAttribute("nickname",user.getNickname());
            redirectAttributes.addFlashAttribute("email",user.getEmail());
            redirectAttributes.addFlashAttribute("phone",user.getPhone());
            List<FieldError> errorList = result.getFieldErrors();
            for(FieldError err : errorList) {
                redirectAttributes.addFlashAttribute("error_"+err.getField(), err.getCode());
            }
            return "redirect:/user/register";
        }
        int cnt = userService.register(user,file);
        model.addAttribute("result",cnt);

        return "/user/registerOk";
    }

    @GetMapping("/findPassword")
    public void findPassword(){}

    @PostMapping("/findPassword")
    public String findPassword(UserDomain userDomain,
                               Model model
    ){

        String userName = userDomain.getUsername();
        String userEmail = userDomain.getEmail();
        String userPhone = userDomain.getPhone();

        userDomain = userService.findByUsername(userName);

        boolean cnt;

        if (userDomain != null && userName.equals(userDomain.getUsername()) &&
                userEmail.equals(userDomain.getEmail()) && userPhone.equals(userDomain.getPhone())){
            cnt = true;
        } else {
            cnt = false;
        }

        model.addAttribute("result", cnt);
        model.addAttribute("user_id",userDomain.getId());

        return "/user/findPasswordOk";
    }

    @GetMapping("/changePassword/{id}")
    public String changePassword(@PathVariable Long id, Model model){
        UserDomain user = userService.findById(id);
        String name = user.getUsername();
        model.addAttribute("username", name);
        return "/user/changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(UserDomain userDomain,
                                 Model model
    ){
        String password = userDomain.getPassword();
        String userName = userDomain.getUsername();

        userDomain = userService.findByUsername(userName);
        userDomain.setPassword(password);

        int cnt = userService.update(userDomain);

        model.addAttribute("result", cnt);

        return "/user/changePasswordOk";
    }



    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(new UserValidator());
    }


}
