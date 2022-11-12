package com.wodongso.wodongso.controller;

import com.wodongso.wodongso.entity.Society;
import com.wodongso.wodongso.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("society")
public class SocietyController {

    @Autowired
    private SocietyService societyService;

    @GetMapping("/apply/{number}")
    public String societyApply(@PathVariable Integer number, Principal principal) {
        System.out.println(number + principal.getName());
        return "redirect:/";
    }


    //    동아리 현황(신청, 거절, 보류 상태 확인)
    @GetMapping("/status-list")
    public String societyStatusList(Model model,
                                    @PageableDefault(page = 0, size = 10, sort = "number", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("list", societyService.societyList(pageable));
        return "societyStatusList";
    }


    //    동아리 전체 조회
//    @GetMapping("/list")
//    public String societyList(Model model,
//                              @PageableDefault(page = 0, size = 10, sort = "number", direction = Sort.Direction.DESC) Pageable pageable) {
//        model.addAttribute("list", societyService.societyList(pageable));
//        return "societyList";
//    }

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
    public String societyDetail(Model model, Integer number) {
        model.addAttribute("society", societyService.societyDetail(number));
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
        System.out.println("principal >> " + principal);
        System.out.println("principal >> " + principal.getName());
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
