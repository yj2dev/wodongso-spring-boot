package com.wodongso.wodongso.service;

import com.wodongso.wodongso.entity.ManagerWithUser;
import com.wodongso.wodongso.entity.User;
import com.wodongso.wodongso.entity.UserManagerStatus;
import com.wodongso.wodongso.repository.UserManagerStatusRepository;
import com.wodongso.wodongso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManagerStatusRepository userManagerStatusRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public boolean userManagerAccept(String id) {
        UserManagerStatus ums = userManagerStatusRepository.findByFromUserId(id);
        ums.setState(true);

        User user = userRepository.findByIdContaining(id);
        user.setRole("ROLE_MANAGER");

        userManagerStatusRepository.save(ums);
        userRepository.save(user);
        return true;
    }

    public boolean userManagerReject(String id, String content) {
        UserManagerStatus ums = userManagerStatusRepository.findByFromUserId(id);
        ums.setState(false);
        ums.setRejectReason(content);

        User user = userRepository.findByIdContaining(id);
        user.setRole("ROLE_USER");

        userManagerStatusRepository.save(ums);
        userRepository.save(user);
        return true;
    }

    public UserManagerStatus userUploadProof(Principal principal,
                                             MultipartFile proofImage) throws IOException {
        UserManagerStatus ums = new UserManagerStatus();
        ums.setFromUserId(principal.getName());
        if (principal.getName() == null) return null;
        if (!proofImage.isEmpty()) {
            String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
            UUID profileUuid = UUID.randomUUID();
            String proofImageName = profileUuid + "_" + proofImage.getOriginalFilename();
            File saveProofImage = new File(filePath, proofImageName);
            proofImage.transferTo(saveProofImage);
            ums.setProofImageUrl("/files/" + proofImageName);
        } else {
            return null;
        }
        return userManagerStatusRepository.save(ums);
    }

    public List<ManagerWithUser> userManagerStatusAll() {
        return userManagerStatusRepository.findAllManagerStatus();
    }

    public boolean userUpdateInfo(Principal principal,
                                  User updateUser,
                                  MultipartFile profileImage) throws Exception {

        User user = userRepository.findByIdContaining(principal.getName());
        user.setName(updateUser.getName());
        user.setNickname(updateUser.getNickname());
        user.setContact(updateUser.getContact());

        if (!profileImage.isEmpty()) {
            String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
            UUID profileUuid = UUID.randomUUID();
            String profileImageName = profileUuid + "_" + profileImage.getOriginalFilename();
            File saveProfileImage = new File(filePath, profileImageName);
            profileImage.transferTo(saveProfileImage);
            user.setProfileUrl("/files/" + profileImageName);
        }

        user.setRegion(updateUser.getRegion());
        user.setUniversity(updateUser.getUniversity());
        user.setMajor(updateUser.getMajor());
        user.setClassOf(updateUser.getClassOf());
        userRepository.save(user);
        return true;
    }


    public boolean userUpdatePassword(Principal principal,
                                      String currentPassword,
                                      String updatePassword,
                                      String updatePasswordCheck) {

        User findUser = userRepository.findByIdContaining(principal.getName());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


//         변경할 비밀번호가 일치한지 확인
        if (!updatePassword.equals(updatePasswordCheck)) {
            return false;
        }

//        현재 비밀번호가 일치한지 확인
        if (encoder.matches(currentPassword, findUser.getPassword()) == true) {
//            변경할 비밀번호 암호화 후 저장
            String encodePassword = passwordEncoder.encode(updatePassword);
            findUser.setPassword(encodePassword);
            userRepository.save(findUser);
            return true;
        }


        return false;
    }

    public User userInfo(String id) {
        return userRepository.findByIdContaining(id);
    }

    public void userRegister(User user, MultipartFile profileImage) throws Exception {

        if (!profileImage.isEmpty()) {
            String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
            UUID profileUuid = UUID.randomUUID();
            String profileImageName = profileUuid + "_" + profileImage.getOriginalFilename();
            File saveProfileImage = new File(filePath, profileImageName);
            profileImage.transferTo(saveProfileImage);
            user.setProfileUrl("/files/" + profileImageName);
        }

        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }
}
