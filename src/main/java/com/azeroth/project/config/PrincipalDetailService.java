package com.azeroth.project.config;

import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.service.AdminService;
import com.azeroth.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //DB조회
        UserDomain user = adminService.findByUsername(username);
        // 해당 username 의 user 가 DB 에 있다면
        // UserDetails 생성해서 리턴
        if( user != null){
            if(user.getU_status().equals("USE")){
                PrincipalDetails userDetails = new PrincipalDetails(user);
                userDetails.setUserService( adminService );
                return userDetails;
            }

        }

        // 해당 username 의 user 가 없다면?
        // UsernameNotFoundException 을 throw 해주어야 한다.
        throw  new UsernameNotFoundException(username);
        // ※ 주의!  여기서 null 리턴하면 예외 발생

    }

}
