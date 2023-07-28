package com.azeroth.project.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = pattern.matcher(username);
        boolean bool = matcher.matches();
        if(!bool){
            errors.rejectValue("username","아이디는 영문,숫자 조합으로 정하셔야합니다.");
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
        Pattern pattern1 = Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");
        Matcher matcher1 = pattern1.matcher(email);
        boolean bool1 = matcher1.matches();
        if(email == null || email.trim().isEmpty()){
            errors.rejectValue("email","이메일은 필수입니다.");
        }
        else if(!bool1){
            errors.rejectValue("email","올바른 양식의 이메일이 아닙니다.");
        }


        String phone = user.getPhone();
        System.out.println(phone);
        Pattern pattern3 = Pattern.compile("^[0-9]{11}$");
        Matcher matcher3 = pattern3.matcher(phone);
        boolean bool2 = matcher3.matches();
        if(phone == null || phone.trim().isEmpty()){
            errors.rejectValue("phone","연락처는 필수입니다.");
        } else if(!bool2){
            errors.rejectValue("phone","연락처는 숫자 11자리 만 가능합니다.");
        }


        if(!user.getPassword().equals(user.getRe_password())){
            errors.rejectValue("re_password", "비밀번호 가 일치하지 않습니다");
        }

    }
}
