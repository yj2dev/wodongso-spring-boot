package com.wodongso.wodongso.controller;

import com.wodongso.wodongso.entity.ManagerWithUser;
import com.wodongso.wodongso.entity.Society;
import com.wodongso.wodongso.entity.User;
import com.wodongso.wodongso.entity.UserManagerStatus;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/user")
public class UserController {

    SessionManager sessionManager = new SessionManager();

    @Autowired
    private UserService userService;

    @Autowired
    private SocietyService societyService;


    @GetMapping("/my-apply-status")
    public String userMyApplyStatus(Model model) {
        List<ManagerWithUser> ums = userService.userManagerStatusAll();
        model.addAttribute("list", ums);
        return "userMyApplyStatus";
    }

    @GetMapping("/my-manager-status")
    public String userMyManagerStatus(Model model) {
        List<ManagerWithUser> ums = userService.userManagerStatusAll();
        model.addAttribute("list", ums);
        return "userMyManagerStatus";
    }


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
    public String userMyInfoDo(Principal principal,
                               User user,
                               MultipartFile profileImage) throws Exception {
        userService.userUpdateInfo(principal, user, profileImage);
        return "redirect:/";
    }


    @PostMapping("/manager-accept/{id}")
    public String userManagerAccept(@PathVariable String id) {
        System.out.println("id >> " + id);
        userService.userManagerAccept(id);
        return "redirect:/user/manager-status-all";
    }

    @PostMapping("/manager-reject/{id}")
    public String userManagerReject(@PathVariable String id, String content) {
        userService.userManagerReject(id, content);
        return "redirect:/user/manager-status-all";
    }

    @GetMapping("/manager-status-all")
    public String userManagerStatusAllDo(Model model) {
        List<ManagerWithUser> ums = userService.userManagerStatusAll();
        model.addAttribute("list", ums);
        return "userManagerStatusAll";
    }


    @GetMapping("/apply-manager")
    public String userApplyManager(Model model, Principal principal) {
        User userInfo = userService.userInfo(principal.getName());
        model.addAttribute("userInfo", userInfo);
        return "userApplyManager";
    }

    @PostMapping("/apply-manager")
    public String userApplyManagerDo(Principal principal, MultipartFile proofImage) throws IOException {
        userService.userUploadProof(principal, proofImage);

        return "redirect:/";
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
}
