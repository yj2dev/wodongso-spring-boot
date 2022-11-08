package com.wodongso.wodongso.service;

import com.wodongso.wodongso.entity.User;
import com.wodongso.wodongso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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
