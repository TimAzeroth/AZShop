package com.azeroth.project.controller;

import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.domain.UserValidator;
import com.azeroth.project.service.UserService;
import com.azeroth.project.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public  String registerOk(@Valid UserDomain user,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes,
                              @RequestParam("profileimg")MultipartFile file
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
        System.out.println("로그컨트롤1");
        int cnt = userService.register(user,file);
        System.out.println("로그컨트롤2");
        model.addAttribute("result",cnt);

        System.out.println("로그컨트롤3");
        return "/user/registerOk";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(new UserValidator());
    }


}
