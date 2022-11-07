package com.wodongso.wodongso.service;

import com.wodongso.wodongso.entity.Society;
import com.wodongso.wodongso.repository.SocietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.util.List;
import java.util.UUID;


@Service
public class SocietyService {

    @Autowired
    private SocietyRepository societyRepository;


    public Page<Society> societySearchList(String searchKeyword, Pageable pageable) {
        return societyRepository.findByNameContaining(searchKeyword, pageable);
    }

    public Page<Society> societyList(Pageable pageable) {
        return societyRepository.findAll(pageable);
    }

    public Society societyDetail(Integer id) {
        return societyRepository.findById(id).get();


    }

    public void societyDelete(Integer number) {
        societyRepository.deleteById(number);
    }

    public void societyCreate(Society society,
                              MultipartFile profileImage,
                              MultipartFile backgroundImage,
                              Principal principal
    ) throws Exception {

        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        System.out.println("filePath >> " + filePath);
        System.out.println("principal >> " + principal);

        if (!profileImage.isEmpty()) {
            UUID profileUuid = UUID.randomUUID();
            String profileImageName = profileUuid + "_" + profileImage.getOriginalFilename();
            File saveProfileImage = new File(filePath, profileImageName);
            profileImage.transferTo(saveProfileImage);
            society.setProfileUrl("/files/" + profileImageName);
        }

        if (!backgroundImage.isEmpty()) {
            UUID backgroundUuid = UUID.randomUUID();
            String backgroundImageName = backgroundUuid + "_" + backgroundImage.getOriginalFilename();
            File saveBackgroundImage = new File(filePath, backgroundImageName);
            backgroundImage.transferTo(saveBackgroundImage);
            society.setBackgroundUrl("/files/" + backgroundImageName);
        }

        society.setOfficer_id(principal.getName());
        society.setEnabled(false);

        societyRepository.save(society);
    }

}
