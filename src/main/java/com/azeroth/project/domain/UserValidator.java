package com.azeroth.project.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        boolean result = UserDomain.class.isAssignableFrom(clazz);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDomain user = (UserDomain) target;

        String username = user.getUsername();
        System.out.println(username);
        if(username == null || username.trim().isEmpty()){
            errors.rejectValue("username","아이디는 필수입니다.");
        }


        String password = user.getPassword();
        System.out.println(password);
        if(password == null || password.trim().isEmpty()){
            errors.rejectValue("password","비밀번호는 필수입니다.");
        }

        String nickname = user.getNickname();
        System.out.println(nickname);
        if(nickname == null || nickname.trim().isEmpty()){
            errors.rejectValue("nickname","이름은 필수입니다.");
        }

        String email = user.getEmail();
        System.out.println(email);
        if(email == null || email.trim().isEmpty()){
            errors.rejectValue("email","이메일은 필수입니다.");
        }

        String phone = user.getPhone();
        System.out.println(phone);
        if(phone == null || phone.trim().isEmpty()){
            errors.rejectValue("phone","연락처는 필수입니다.");
        }


//        if(!user.getPassword().equals(user.getRe_password())){
//            errors.rejectValue("re_password", "비밀번호 가 일치하지 않습니다");
//        }

    }
}
