package com.wodongso.wodongso.controller;

import com.wodongso.wodongso.entity.Society;
import com.wodongso.wodongso.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SocietyController {

    @Autowired
    private SocietyService societyService;


    @GetMapping("/society/create")
    public String societyCreate() {
        return "societyCreate";
    }

    @GetMapping("/society/list")
    public String societyList(Model model) {
        model.addAttribute("list", societyService.societyList());
        return "societyList";
    }

    @GetMapping("/society/detail")
    public String societyDetail(Model model, Integer number) {
        model.addAttribute("society", societyService.societyDetail(number));
        return "societyDetail";
    }

//    public String createSocietyDo(String title, String desc)

}
