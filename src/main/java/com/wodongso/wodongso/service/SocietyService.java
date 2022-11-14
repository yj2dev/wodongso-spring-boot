package com.wodongso.wodongso.service;

import com.wodongso.wodongso.dto.SocietyWithUser;
import com.wodongso.wodongso.entity.*;
import com.wodongso.wodongso.repository.SocietyCreateStatusRepository;
import com.wodongso.wodongso.repository.SocietyRecruitStatusRepository;
import com.wodongso.wodongso.repository.SocietyRepository;
import com.wodongso.wodongso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class SocietyService {


    @Autowired
    private SocietyRepository societyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SocietyCreateStatusRepository societyCreateStatusRepository;

    @Autowired
    private SocietyRecruitStatusRepository societyRecruitStatusRepository;

    public void societyMyApply(int number, Principal principal) {
        SocietyRecruitStatus srs = societyRecruitStatusRepository
                .findByToSocietyNumberAndFromUserIdContaining(number, principal.getName());
        System.out.println(srs.getToSocietyNumber());
    }


    public boolean societyRecruitApply(Integer number, Principal principal) {
        SocietyRecruitStatus srs = new SocietyRecruitStatus();
//        User user = new User();
//        user.setId(principal.getName());
//        srs.setFromUserId(user);

//        Society society = new Society();
//        society.setNumber(number);
//        srs.setToSocietyNumber(society);

        srs.setFromUserId(principal.getName());
        srs.setToSocietyNumber(number);
        srs.setState(0);

        societyRecruitStatusRepository.save(srs);
        return true;
    }

    public boolean societyCreateAccept(Integer number, Principal principal) {
        Optional<Society> society = societyRepository.findById(number);
        society.get().setEnabled(1);

        SocietyCreateStatus scs = new SocietyCreateStatus();
        scs.setToSocietyNumber(number);
        scs.setFromUserId(principal.getName());
        scs.setState(1);

        societyCreateStatusRepository.save(scs);
        societyRepository.save(society.get());
        return true;
    }

    public boolean societyCreateReject(Integer number, String content, Principal principal) {
        Optional<Society> society = societyRepository.findById(number);
        society.get().setEnabled(-1);

        SocietyCreateStatus scs = new SocietyCreateStatus();
        scs.setToSocietyNumber(number);
        scs.setFromUserId(principal.getName());
        scs.setState(-1);

        if (content.length() == 0) {
            return false;
        }

        scs.setRejectReason(content);

        societyCreateStatusRepository.save(scs);
        societyRepository.save(society.get());
        return true;
    }


    public List<SocietyWithUser> societyStatusList(Pageable pageable, Principal principal) {

        User user = userRepository.findByIdContaining(principal.getName());
        return societyRepository.findAllByUniversity(user.getUniversity());
    }

    public Page<Society> societyEnableList(Pageable pageable) {
        return societyRepository.findByEnabledPage(1, pageable);
    }

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

        User user = new User();
        user.setId(principal.getName());

        society.setOfficerId(user);
        Society saveSociety = societyRepository.save(society);

        SocietyCreateStatus scs = new SocietyCreateStatus();
        scs.setToSocietyNumber(saveSociety.getNumber());
        scs.setFromUserId(principal.getName());
        scs.setState(0);
        societyCreateStatusRepository.save(scs);
    }

}
