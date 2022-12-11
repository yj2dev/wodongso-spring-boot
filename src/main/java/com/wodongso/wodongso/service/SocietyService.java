package com.wodongso.wodongso.service;

import com.wodongso.wodongso.dto.CategoryDetail;
import com.wodongso.wodongso.dto.MySocietyList;
import com.wodongso.wodongso.dto.SocietyRecruitWithUserOfficer;
import com.wodongso.wodongso.dto.SocietyWithUser;
import com.wodongso.wodongso.entity.*;
import com.wodongso.wodongso.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
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

    @Autowired
    private SocietyCategoryRepository societyCategoryRepository;

    @Autowired
    private SocietyContentRepository societyContentRepository;

    @Autowired
    private SocietyContentImageRepository societyContentImageRepository;

    //    내가 가입하거나 생성한 동아리 목록 가져오기
    public List<MySocietyList> getMySocietyList(Principal principal) {
        List<MySocietyList> createList = societyCreateStatusRepository.findMySocietyCreate(principal.getName());
        List<MySocietyList> recruitList = societyRecruitStatusRepository.findMySocietyRecruit(principal.getName());
        List<MySocietyList> resultList = new ArrayList<>();
        resultList.addAll(createList);
        resultList.addAll(recruitList);
        return resultList;
    }

    //    카테고리 게시글 상세정보 가져오기
    public List<CategoryDetail> getCategoryDetail(Integer scid) {
        return societyContentRepository.findByIdJoinUserAndImage(scid);

    }


    //    해당 동아리 기본게시판(활동내역) 번호 가져오기
    public SocietyCategory getCategoryId(Integer number) {
        return societyCategoryRepository.findByFromSocietyNumberAndName(number, "활동내역");
    }

    //    해당하는 카테고리 글 가져오기
    public List<SocietyContent> getCategoryContent(Integer cid) {
        return societyContentRepository.findByToCategoryId(cid);
    }


    //    카테고리에 맞게 글 작성
    public boolean categoryBoardWrite(Principal principal, Integer number, Integer categoryId, String title, String content, List<MultipartFile> files) throws IOException {

        System.out.println("files >> " + files);
//         게시글 저장
        SocietyContent sc = new SocietyContent();
        sc.setToCategoryId(categoryId);
        sc.setFromSocietyNumber(number);
        sc.setTitle(title);
        sc.setWriterId(principal.getName());
        sc.setContent(content);
        SocietyContent saveSc = societyContentRepository.save(sc);

// 게시글에 등록된 이미지 저장

        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        if (files.size() > 0) {
            boolean isThumbnail = false;
            for (MultipartFile file : files) {
                SocietyContentImage sci = new SocietyContentImage();
                sci.setFromSocietyContentId(saveSc.getId());
                UUID imageUuid = UUID.randomUUID();
                String imageName = file.getOriginalFilename() + "_" + imageUuid + ".jpg";
                File saveImage = new File(filePath, imageName);
                file.transferTo(saveImage);
                sci.setPath("/files/" + imageName);

                if (isThumbnail == false) {
                    saveSc.setThumbnailUrl("/files/" + imageName);
                    societyContentRepository.save(saveSc);
                }

                societyContentImageRepository.save(sci);
                isThumbnail = true;
            }
        }


        return true;

    }

    //    카테고리 생성
    public SocietyCategory categoryCreate(Principal principal, Integer number, String name) {
        if (principal.getName() == null) return null;

        SocietyCategory sc = new SocietyCategory();
        sc.setFromSocietyNumber(number);
        sc.setName(name);
        return societyCategoryRepository.save(sc);
    }

    ;

    //    해당 동아리가 사용하는 카테고리 가져오기
    public List<SocietyCategory> getCategory(Integer number) {
        return societyCategoryRepository.findByFromSocietyNumber(number);
    }

    //    동아리 가입 신청자 수락
    public SocietyRecruitStatus societyRecruitAcceptUser(Principal principal, Integer uid) {
        if (principal.getName() == null) return null;

        Optional<SocietyRecruitStatus> srs = societyRecruitStatusRepository.findById(uid);
        srs.get().setState(1);

        return societyRecruitStatusRepository.save(srs.get());
    }

    //    동아리 가입 신청자 거절
    public SocietyRecruitStatus societyRecruitRejectUser(Principal principal, Integer uid, String content) {
        if (principal.getName() == null) return null;

        Optional<SocietyRecruitStatus> srs = societyRecruitStatusRepository.findById(uid);
        srs.get().setState(-1);
        srs.get().setRejectReason(content);

        return societyRecruitStatusRepository.save(srs.get());
    }


    //    동아리 관리(동아리 신청한 명단 조회)
    public List<SocietyRecruitWithUserOfficer> societyRecruitUser(Integer number) {
        return societyRecruitStatusRepository.findBySocietyRecruitJoinUser(number);
    }


    //    동아리 가입 신청
    public boolean societyRecruitApply(Integer number, Principal principal) {
        SocietyRecruitStatus srs = new SocietyRecruitStatus();
        srs.setFromUserId(principal.getName());
        srs.setToSocietyNumber(number);
        srs.setState(0);

        societyRecruitStatusRepository.save(srs);
        return true;
    }

    //    동아리 개설 신청 수락
    public boolean societyCreateAccept(Integer number, Principal principal) {
        Optional<Society> society = societyRepository.findById(number);
        society.get().setEnabled(1);

//        SocietyCreateStatus scs = new SocietyCreateStatus();
//        scs.setToSocietyNumber(number);
//        scs.setFromUserId(principal.getName());
//        scs.setState(1);

        SocietyCreateStatus scs = societyCreateStatusRepository.findByToSocietyNumberAndFromUserId(
                society.get().getNumber(),
                society.get().getOfficerId().getId());
        scs.setToSocietyNumber(society.get().getNumber());
        scs.setFromUserId(society.get().getOfficerId().getId());
        scs.setState(1);


        societyCreateStatusRepository.save(scs);
        societyRepository.save(society.get());
        return true;
    }

    //    동아리 개설 신청 거절
    public boolean societyCreateReject(Integer number, String content, Principal principal) {
        Optional<Society> society = societyRepository.findById(number);
        society.get().setEnabled(-1);

//        SocietyCreateStatus scs = new SocietyCreateStatus();
        SocietyCreateStatus scs = societyCreateStatusRepository.findByToSocietyNumberAndFromUserId(
                society.get().getNumber(),
                society.get().getOfficerId().getId());
        scs.setToSocietyNumber(society.get().getNumber());
        scs.setFromUserId(society.get().getOfficerId().getId());
        scs.setState(-1);


        if (content.length() == 0) {
            return false;
        }

        scs.setRejectReason(content);

        societyCreateStatusRepository.save(scs);


        societyRepository.save(society.get());
        return true;
    }


    //    동아리 상태 조회(보류 0, 등록 1, 미등록 -1)
//     해당 학교만 조회
    public List<SocietyWithUser> societyStatusList(Pageable pageable, Principal principal) {
        User user = userRepository.findByIdContaining(principal.getName());
        return societyRepository.findAllByUniversity(user.getUniversity());
    }

    //    등록된 동아리 목록
    public Page<Society> societyEnableList(Pageable pageable) {
        return societyRepository.findByEnabledPage(1, pageable);
    }

    //    등록된 동아리 중 검색된 목록
    public Page<Society> societySearchList(String searchKeyword, Pageable pageable) {
        return societyRepository.findByNameContainingAndEnabled(searchKeyword, 1, pageable);
//        return societyRepository.findByNameContaining(searchKeyword, pageable);
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



    //    동아리 정보 수정
    public void societyUpdate(Integer number,
                              Society society,
                              MultipartFile profileImage,
                              MultipartFile backgroundImage,
                              Principal principal) throws IOException {
        Society societyOrigin = societyRepository.findById(number).get();
        societyOrigin.setSimpleDesc(society.getSimpleDesc());
        societyOrigin.setDetailDesc(society.getDetailDesc());
        societyOrigin.setPosition(society.getPosition());

        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        if (!profileImage.isEmpty()) {
            UUID profileUuid = UUID.randomUUID();
            String profileImageName = profileUuid + "_" + profileImage.getOriginalFilename() + ".jpg";
            File saveProfileImage = new File(filePath, profileImageName);
            profileImage.transferTo(saveProfileImage);
            societyOrigin.setProfileUrl("/files/" + profileImageName);
        }

        if (!backgroundImage.isEmpty()) {
            UUID backgroundUuid = UUID.randomUUID();
            String backgroundImageName = backgroundUuid + "_" + backgroundImage.getOriginalFilename() + ".jpg";
            File saveBackgroundImage = new File(filePath, backgroundImageName);
            backgroundImage.transferTo(saveBackgroundImage);
            societyOrigin.setBackgroundUrl("/files/" + backgroundImageName);
        }




        societyRepository.save(societyOrigin);
    }
    //    동아리 개설 신청
    public void societyCreate(Society society,
                              MultipartFile profileImage,
                              MultipartFile backgroundImage,
                              Principal principal
    ) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        if (!profileImage.isEmpty()) {
            UUID profileUuid = UUID.randomUUID();
            String profileImageName = profileUuid + "_" + profileImage.getOriginalFilename() + ".jpg";
            File saveProfileImage = new File(filePath, profileImageName);
            profileImage.transferTo(saveProfileImage);
            society.setProfileUrl("/files/" + profileImageName);
        }

        if (!backgroundImage.isEmpty()) {
            UUID backgroundUuid = UUID.randomUUID();
            String backgroundImageName = backgroundUuid + "_" + backgroundImage.getOriginalFilename() + ".jpg";
            File saveBackgroundImage = new File(filePath, backgroundImageName);
            backgroundImage.transferTo(saveBackgroundImage);
            society.setBackgroundUrl("/files/" + backgroundImageName);
        }


        User user = new User();
        user.setId(principal.getName());

//        동아리 삽입
        society.setOfficerId(user);
        society.setEnabled(0);
        Society saveSociety = societyRepository.save(society);

//        동아리 등록상태 삽입
        SocietyCreateStatus scs = new SocietyCreateStatus();
        scs.setToSocietyNumber(saveSociety.getNumber());
        scs.setFromUserId(principal.getName());
        scs.setState(0);
        societyCreateStatusRepository.save(scs);

        // 기본 제공되는 게시판 카테고리 추가(활동 게시판)
        SocietyCategory sc = new SocietyCategory();
        sc.createDefault(saveSociety.getNumber());
        societyCategoryRepository.save(sc);
    }

}
