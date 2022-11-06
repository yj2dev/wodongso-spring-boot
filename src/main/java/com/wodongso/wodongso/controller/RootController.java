package com.wodongso.wodongso.controller;

import com.wodongso.wodongso.entity.Society;
import com.wodongso.wodongso.security.SessionManager;
import com.wodongso.wodongso.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class RootController {

    @Autowired
    SocietyService societyService;
    SessionManager sessionManager = new SessionManager();


    @GetMapping("/")
    public String root(Model model,
                       @PageableDefault(page = 0, size = 10, sort = "number", direction = Sort.Direction.DESC) Pageable pageable,
                       String searchKeyword,
                       HttpServletResponse res) {
        Page<Society> list = null;

        if (searchKeyword == null) {
            list = societyService.societyList(pageable);
        } else {
            list = societyService.societySearchList(searchKeyword, pageable);
        }

        int currentPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(currentPage - 4, 1);
        int endPage = Math.min(currentPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        sessionManager.createSession("4412-3frk3-1293", res);

        return "index";
    }

}
