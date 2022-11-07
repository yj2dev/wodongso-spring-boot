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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.UUID;


@Controller
@RequestMapping("/user")
public class UserController {

    SessionManager sessionManager = new SessionManager();

    @Autowired
    private UserService userService;

    @Autowired
    private SocietyService societyService;


    @GetMapping("/info")
    @ResponseBody
    public User userInfo(String userId) {
        System.out.println("userId >> " + userId);
        return userService.userInfo(userId);
    }

    @GetMapping("/login")
    public String userLogin() {
        return "userLogin";
    }

    @PostMapping("/login")
    public String userLoginDo() {
        return "userLogin";
    }


    @GetMapping("/register")
    public String userRegister(HttpServletRequest req) {
//        System.out.println(sessionManager.getAllSession(req));

        return "userRegister";
    }

    @PostMapping("/register")
    public String userRegisterDo(User user, MultipartFile profileImage) throws Exception {
        userService.userRegister(user, profileImage);
        return "redirect:/user/login";
    }
//    @GetMapping("/logout")
//    public String userLogout(HttpServletRequest req) {
//        sessionManager.expire(req);
//        return "redirect:/";
//    }

//    @PostMapping("/logindo")
//    @ResponseBody
//    public String userLoginDo(User user) {
////        System.out.println('user >> ', user);
//
//
//        return null;
//    }

}
