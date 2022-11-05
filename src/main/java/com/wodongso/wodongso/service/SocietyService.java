package com.wodongso.wodongso.service;

import com.wodongso.wodongso.entity.Society;
import com.wodongso.wodongso.repository.SocietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
                              MultipartFile backgroundImage) throws Exception {
        System.out.println(" p >> " + profileImage.isEmpty());
        System.out.println(" b >> " + backgroundImage.isEmpty());
        //        if (profileImage.isEmpty()) {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID profileUuid = UUID.randomUUID();
        UUID backgroundUuid = UUID.randomUUID();

        String profileImageName = profileUuid + "_" + profileImage.getOriginalFilename();
        String backgroundImageName = backgroundUuid + "_" + backgroundImage.getOriginalFilename();

        File saveProfileImage = new File(filePath, profileImageName);
        File saveBackgroundImage = new File(filePath, backgroundImageName);

        profileImage.transferTo(saveProfileImage);
        backgroundImage.transferTo(saveBackgroundImage);

        society.setProfileUrl("/files/" + profileImageName);
        society.setBackgroundUrl("/files/" + backgroundImageName);
//        }

        societyRepository.save(society);
    }

}
