package com.wodongso.wodongso.controller;

import com.wodongso.wodongso.entity.Society;
import com.wodongso.wodongso.entity.User;
import com.wodongso.wodongso.security.SessionManager;
import com.wodongso.wodongso.service.SocietyService;
import com.wodongso.wodongso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Controller
public class UserController {

    SessionManager sessionManager = new SessionManager();

    @Autowired
    private UserService userService;
    @Autowired
    private SocietyService societyService;

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


    @GetMapping("/user/register")
    public String userRegister() {
        return "userRegister";
    }

    @GetMapping("/user/login")
    public String userLogin() {
        return "userLogin";
    }

    @PostMapping("/user/logindo")
    @ResponseBody
    public String userLoginDo(User user) {
//        System.out.println('user >> ', user);


        return null;
    }

    @PostMapping("/user/registerdo")
    @ResponseBody
    public String userRegisterDo(User user) {
        userService.userRegister(user);
        return null;
    }

}
