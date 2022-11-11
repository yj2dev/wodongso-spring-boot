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


    @GetMapping("/update-password")
    public String userUpdatePassword() {
        return "userUpdatePassword";
    }

    @PostMapping("/update-password")
    public String userUpdatePasswordDo(Principal principal,
                                       String currentPassword,
                                       String updatePassword,
                                       String updatePasswordCheck
    ) {
        System.out.println("principal.getName()" + principal.getName());
        System.out.println("currentPassword >> " + currentPassword);
        System.out.println("updatePassword" + updatePassword);
        System.out.println("updatePasswordCheck" + updatePasswordCheck);

        if (userService.userUpdatePassword(principal, currentPassword, updatePassword, updatePasswordCheck) == true) {
            return "redirect:/user/login";
        } else {
            return "redirect:/user/update-password?fail=true";
        }
    }

    @GetMapping("/my-info")
    public String userMyInfo(Model model, Principal principal) {
        User userInfo = userService.userInfo(principal.getName());
        model.addAttribute("userInfo", userInfo);
        return "userMyInfo";
    }

    @PostMapping("/my-info")
    public String userMyInfoDo(Principal principal) {

        return "userMyInfo";
    }

    @GetMapping("/apply-manager")
    public String userApplyManager(Model model, Principal principal) {
        User userInfo = userService.userInfo(principal.getName());
        model.addAttribute("userInfo", userInfo);
        return "userApplyManager";
    }

    @GetMapping("/info")
    @ResponseBody
    public User userInfo(String userId) {
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
