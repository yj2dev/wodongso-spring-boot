package com.wodongso.wodongso.controller;

import com.wodongso.wodongso.dto.CategoryDetail;
import com.wodongso.wodongso.entity.Society;
import com.wodongso.wodongso.dto.SocietyWithUser;
import com.wodongso.wodongso.entity.SocietyCategory;
import com.wodongso.wodongso.entity.SocietyContent;
import com.wodongso.wodongso.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("society")
public class SocietyController {

    @Autowired
    private SocietyService societyService;


    //    카테고리 상세 페이지 이동
    @GetMapping("/category/detail")
    public String societyCategoryDetail(Model model, Integer scid) {
        List<CategoryDetail> cd = societyService.getCategoryDetail(scid);
        model.addAttribute("payload", cd);
        return "societyCategoryDetail";
    }


    //    동아리 게시글 작성
    @PostMapping("/category-board/write")
    public String societyCategoryBoardWrite(Principal principal,
                                            Integer number,
                                            Integer categoryId,
                                            String title,
                                            String content,
                                            List<MultipartFile> files
    ) throws IOException {
        societyService.categoryBoardWrite(principal, number, categoryId, title, content, files);

        return String.format("redirect:/society/detail?number=%d&cid=%d", number, categoryId);
    }


    //    동아리 카테고리 추가
    @PostMapping("/category/create")
    public String societyCategoryCreate(Principal principal, Integer number, String name) {
        societyService.categoryCreate(principal, number, name);
        return String.format("redirect:/society/detail?number=%d", number);
    }


    //   동아리 가입 신청자 수락
    @PostMapping("/recruit/accept")
    public String societyRecruitApplyUser(Principal principal, Integer uid, Integer number) {
        societyService.societyRecruitAcceptUser(principal, uid);
        return String.format("redirect:/society/detail?number=%d", number);
    }

    //   동아리 가입 신청자 거절
    @PostMapping("/recruit/reject")
    public String societyRecruitRejectUser(Principal principal, Integer uid, String content, Integer number) {
        societyService.societyRecruitRejectUser(principal, uid, content);
        return String.format("redirect:/society/detail?number=%d", number);
    }

    //    동아리 가입 신청
    @GetMapping("/apply/{number}")
    public String societyRecruitApply(@PathVariable Integer number, Principal principal) {
        System.out.println(number + principal.getName());
        societyService.societyRecruitApply(number, principal);
        return "redirect:/";
    }


    //    동아리 개설 허용
    @PostMapping("/accept/{number}")
    public String societyCreateAccept(@PathVariable Integer number, Principal principal) {
        societyService.societyCreateAccept(number, principal);
        return "redirect:/society/status-list";
    }

    @PostMapping("/reject/{number}")
    public String societyCreateReject(@PathVariable Integer number, Principal principal, String content) {
        societyService.societyCreateReject(number, content, principal);
        return "redirect:/society/status-list";
    }


    //    동아리 현황(신청, 거절, 보류 상태 확인)
    @GetMapping("/status-list")
    public String societyStatusList(Model model,
                                    @PageableDefault(page = 0, size = 10,
                                            sort = "number",
                                            direction = Sort.Direction.DESC)
                                    Pageable pageable,
                                    Principal principal) {
        List<SocietyWithUser> list = societyService.societyStatusList(pageable, principal);
        model.addAttribute("list", list);
        return "societyStatusList";
    }

    @GetMapping("/open-list")
    public String societyOpenList(Model model,
                                  @PageableDefault(page = 0, size = 10,
                                          sort = "number", direction = Sort.Direction.DESC)
                                  Pageable pageable) {

        model.addAttribute("list", societyService.societyEnableList(pageable));

        return "societyList";
    }


    // 특정 동아리 조회
    @GetMapping("/detail")
    public String societyDetail(Model model, Integer cid, Integer number) {
        model.addAttribute("society", societyService.societyDetail(number));
        model.addAttribute("recruitUserList", societyService.societyRecruitUser(number));
        model.addAttribute("categoryList", societyService.getCategory(number));

        if (cid != null) {
            List<SocietyContent> contentList = societyService.getCategoryContent(cid);
            model.addAttribute("contentList", contentList);
        } else {
            SocietyCategory sc = societyService.getCategoryId(number);
            cid = sc.getId();
            List<SocietyContent> contentList = societyService.getCategoryContent(cid);
            model.addAttribute("contentList", contentList);
        }

        model.addAttribute("currentCategoryId", cid);

        return "societyDetail";
    }

    // 동아리 생성페이지 이동
    @GetMapping("/create")
    public String societyCreate() {
        return "societyCreate";
    }

    //    동아리 생성
    @PostMapping("/create")
    public String createSocietyDo(Society society,
                                  MultipartFile profileImage,
                                  MultipartFile backgroundImage,
                                  Principal principal
    ) throws Exception {
        societyService.societyCreate(society, profileImage, backgroundImage, principal);
        return "redirect:/";
    }


    // 동아리 제거
    @GetMapping("/delete")
    public String societyDelete(Integer number) {
        societyService.societyDelete(number);
        return "redirect:/";
    }

    //    동아리 정보 수정페이지 이동
    @GetMapping("/update/{number}")
    public String societyUpdate(@PathVariable Integer number, Model model) {
        model.addAttribute("society", societyService.societyDetail(number));
        return "societyUpdate";
    }


    //    동아리 정보 수정
    @PostMapping("/update/{number}")
    public String societyUpdateDo(@PathVariable Integer number,
                                  Society society,
                                  MultipartFile profileImage,
                                  MultipartFile backgroundImage,
                                  Principal principal
    ) throws Exception {
        Society societyOrigin = societyService.societyDetail(number);
        societyOrigin.setSimpleDesc(society.getSimpleDesc());
        societyOrigin.setDetailDesc(society.getDetailDesc());
        societyOrigin.setPosition(society.getPosition());
        societyService.societyCreate(societyOrigin, profileImage, backgroundImage, principal);
        return "redirect:/";
    }


}
