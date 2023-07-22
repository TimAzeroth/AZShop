package com.azeroth.project.config;

import com.azeroth.project.domain.AuthorityDomain;
import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.service.AdminService;
import com.azeroth.project.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PrincipalDetails implements UserDetails {

    private AdminService adminService;

    public void setUserService(AdminService userService) {
        this.adminService = userService;
    }
    private UserDomain user;

    public void setUser(UserDomain user) {
        this.user = user;
    }

    public UserDomain getUserDomain(){
        return user;
    }

    public PrincipalDetails(UserDomain user){
        System.out.println("UserDetails(user) 생성: " + user);
        this.user = user;
    }

    // 해당 User 의 '권한(들)'을 리턴
    // 현재 로그인한 사용자의 권한정보가 필요할때마다 호출된다. 혹은 필요할때마다 직접 호출해 사용할수도 있다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("getAuthorities() 호출");

        Collection<GrantedAuthority> collect = new ArrayList<>();

        List<AuthorityDomain> list =  adminService.selectAuthoritiesById(user.getId());  // DB 에서 user 의 권한(들) 읽어오기

        for(AuthorityDomain auth : list){
            collect.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return auth.getName();
                }

//                 thymeleaf 등에서 활용하려구. (학습목적)
//                @Override
//                public String toString() {
//                    return auth.getName();
//                }
            });
        }

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정이 만료된건 아닌지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠긴건 아닌지.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    // 계정 credential 이 만료된건 아닌지.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 활성화 되어있는지.
    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDomain getUser() {
        return user;
    }
}