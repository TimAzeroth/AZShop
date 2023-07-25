package com.azeroth.project.service;

import com.azeroth.project.domain.AddressDomain;
import com.azeroth.project.domain.AuthorityDomain;
import com.azeroth.project.domain.UserDomain;
import com.azeroth.project.repository.AuthorityRepository;
import com.azeroth.project.repository.UserRepository;
import com.azeroth.project.util.Util;
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
import java.util.List;

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
    public int updatePassword(UserDomain user) {
        return userRepository.update(user);
    }

    @Override
    public int update(Integer isDelete, String originalImage, UserDomain user, MultipartFile multipartFile) {

        if(isDelete == 1){
            delFile(originalImage);
            user.setProfileimg(null);
        } else if(isDelete == 0){
            delFile(originalImage);
            String originalFilename = multipartFile.getOriginalFilename();

            if(originalFilename == null || originalFilename.length()== 0){
                user.setProfileimg(null);
                return userRepository.update(user);
            }

            String sourceName = StringUtils.cleanPath(originalFilename);

            String fileName = sourceName;

            File file1 = new File(uploadDir + File.separator + sourceName);
            if(file1.exists()) {
                int pos = fileName.lastIndexOf(".");
                if(pos > -1){
                    String name = fileName.substring(0, pos);
                    String ext = fileName.substring(pos + 1);

                    fileName = name + "_" + System.currentTimeMillis() + "." + ext;
                } else {
                    fileName += "_" + System.currentTimeMillis();
                }
            }

            user.setProfileimg(fileName);

            Path copyOfLocation = Paths.get(new File(uploadDir + File.separator + fileName).getAbsolutePath());

            try {
                Files.copy(
                        multipartFile.getInputStream(),
                        copyOfLocation,
                        StandardCopyOption.REPLACE_EXISTING
                );
            } catch (IOException e){
                e.printStackTrace();
            }
            return userRepository.update(user);
        }

        return userRepository.update(user);
    }

    @Override
    public int delete(Long id) {
        return userRepository.delete(id);
    }

    // 특정 회원 주소 추가
    @Override
    public int addAddress(AddressDomain addressDomain) {
        UserDomain user = Util.getLoggedUser();

        user = userRepository.findById(user.getId());
        addressDomain.setUser_id(user.getId());

        return userRepository.postInsert(addressDomain);
    }

    @Override
    public List<AddressDomain> findAddressByUserId(Long user_id) {
        return userRepository.selectByUserId(user_id);
    }

    // 주소 추가
    @Override
    public int saveAddress(AddressDomain addressDomain) {
        return userRepository.postInsert(addressDomain);
    }

    @Override
    public int deleteAddress(AddressDomain addressDomain) {
        Long id = addressDomain.getId();
        return userRepository.postDelete(id);
    }

    @Override
    public AddressDomain findAddressById(Long id) {
        return userRepository.selectById(id);
    }

    private void delFile(String originalImage) {
        String saveDirectory = new File(uploadDir).getAbsolutePath();

        File file = new File(saveDirectory, originalImage);
        file.delete();
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
