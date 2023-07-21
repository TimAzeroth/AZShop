package com.azeroth.project.service;

import com.azeroth.project.domain.AuthorityDomain;
import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.repository.AuthorityRepository;
import com.azeroth.project.repository.UserRepository;
import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UserServiceImpl implements UserService{

    @Value("${app.upload.path}")
    private String uploadDir;

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(SqlSession sqlSession){
        userRepository = sqlSession.getMapper(UserRepository.class);
        authorityRepository = sqlSession.getMapper(AuthorityRepository.class);
    }

    @Override
    public UserDomain findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDomain findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isExist(String username) {
        UserDomain user = findByUsername(username);
        return (user != null) ? true : false;
    }

    @Override
    public int register(UserDomain user, MultipartFile multipartFile) {
        return upload(user,multipartFile);
    }

    @Override
    public int update(UserDomain user) {
        return userRepository.update(user);
    }


    private int upload(UserDomain user, MultipartFile multipartFile) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));   // password 는 암호화 해서 저장 (추후 인코딩 설정)
        AuthorityDomain auth = authorityRepository.findByName("ROLE_MEMBER");
        user.setAuthority_id(auth.getId());
        user.setU_status("USE");    //  상태 "USE" 로 기본 설정

        String originalFilename = multipartFile.getOriginalFilename();

        if (originalFilename == null || originalFilename.length() == 0) {
            user.setProfileimg(null);
            return userRepository.insert(user);
        }


        //원본 파일명
        String sourceName = StringUtils.cleanPath(originalFilename);
        //저장될 파일명
        String fileName = sourceName;
        // 중복확인
        File file = new File(uploadDir + File.separator + sourceName);
        if(file.exists()) {
            int pos = fileName.lastIndexOf(".");
            if (pos > -1) {
                String name = fileName.substring(0, pos);
                String ext = fileName.substring(pos + 1);

                fileName = name + "_" + System.currentTimeMillis() + "." + ext;
            } else {
                fileName += "_" + System.currentTimeMillis();
            }
        }

        user.setProfileimg(fileName);

        // nio
        Path copyOfLocation = Paths.get(new File(uploadDir + File.separator + fileName).getAbsolutePath());

        try{
            Files.copy(
                    multipartFile.getInputStream(),
                    copyOfLocation,
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userRepository.insert(user);
    }
}
