package com.wodongso.wodongso.controller;

import com.wodongso.wodongso.entity.Society;
import com.wodongso.wodongso.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
public class SocietyController {

    @Autowired
    private SocietyService societyService;

    //    동아리 전체 조회
    @GetMapping("/society/list")
    public String societyList(Model model) {
        model.addAttribute("list", societyService.societyList());
        return "societyList";
    }

    // 특정 동아리 조회
    @GetMapping("/society/detail")
    public String societyDetail(Model model, Integer number) {
        model.addAttribute("society", societyService.societyDetail(number));
        return "societyDetail";
    }

    // 동아리 생성페이지 이동
    @GetMapping("/society/create")
    public String societyCreate() {
        return "societyCreate";
    }

    //    동아리 생성
    @PostMapping("/society/createdo")
    public String createSocietyDo(Society society,
                                  MultipartFile profileImage,
                                  MultipartFile backgroundImage) throws Exception {
        System.out.println("file >> " + profileImage);

        societyService.societyCreate(society, profileImage, backgroundImage);

        return "redirect:/";
    }


    // 동아리 제거
    @GetMapping("/society/delete")
    public String societyDelete(Integer number) {
        System.out.println("numbers >> " + number);
        societyService.societyDelete(number);
        return "redirect:/";
    }

    //    동아리 정보 수정페이지 이동
    @GetMapping("/society/update/{number}")
    public String societyUpdate(@PathVariable Integer number, Model model) {
        model.addAttribute("society", societyService.societyDetail(number));

        //        societyService.societyDelete(number);
        return "societyUpdate";
    }


    //    동아리 정보 수정
    @PostMapping("/society/updatedo/{number}")
    public String societyUpdateDo(@PathVariable Integer number,
                                  Society society,
                                  MultipartFile profileImage,
                                  MultipartFile backgroundImage) throws Exception {
        Society societyOrigin = societyService.societyDetail(number);
        societyOrigin.setSimpleDesc(society.getSimpleDesc());
        societyOrigin.setDetailDesc(society.getDetailDesc());
        societyOrigin.setPosition(society.getPosition());
        societyService.societyCreate(societyOrigin, profileImage, backgroundImage);
        return "redirect:/";
    }


}
