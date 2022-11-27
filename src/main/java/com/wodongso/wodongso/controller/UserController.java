package com.wodongso.wodongso.controller;

import com.wodongso.wodongso.dto.ManagerWithUser;
import com.wodongso.wodongso.dto.SocietyCreateWithUser;
import com.wodongso.wodongso.dto.SocietyRecruitWithUser;
import com.wodongso.wodongso.entity.User;
import com.wodongso.wodongso.entity.UserManagerStatus;
import com.wodongso.wodongso.security.SessionManager;
import com.wodongso.wodongso.service.SocietyService;
import com.wodongso.wodongso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    SessionManager sessionManager = new SessionManager();

    @Autowired
    private UserService userService;

    @Autowired
    private SocietyService societyService;


    // 유저 아이디 중복 확인
    @GetMapping("/id-check")
    @ResponseBody
    public boolean userIsId(@RequestParam String id) {
        return userService.isId(id);
    }

    //    내 동아리 개설 신청 내역 조회
    @GetMapping("/my-create-status")
    public String userMyCreateStatus(Model model, Principal principal) {
        List<SocietyCreateWithUser> uws = userService.myCreateStatus(principal);
        model.addAttribute("list", uws);
        return "userMyCreateStatus";
    }

    //    내 동아리 가입 신청 내역 조회
    @GetMapping("/my-apply-status")
    public String userMyApplyStatus(Model model, Principal principal) {
        List<SocietyRecruitWithUser> srw = userService.myApplyStatus(principal);
        model.addAttribute("list", srw);
        return "userMyApplyStatus";
    }

    //    내 동아리 관리자(매니저) 결과 조회
    @GetMapping("/my-manager-status")
    public String userMyManagerStatus(Model model, Principal principal) {
        UserManagerStatus ums = userService.myManagerStatus(principal);
        User user = userService.userInfo(principal.getName());
        model.addAttribute("list", ums);
        model.addAttribute("university", user.getUniversity());
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


    //    매니저 신청자 제거
    @PostMapping("/manager-delete/{id}")
    public String userManagerDelete(@PathVariable String id) {
        userService.userManagerDelete(id);
        return "redirect:/user/manager-status-all";
    }

    @PostMapping("/manager-accept/{id}")
    public String userManagerAccept(@PathVariable String id) {
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
        return "content/user/login/userLogin";
    }

    @PostMapping("/login")
    public String userLoginDo() {
        return "content/user/login/userLogin";
    }


    @GetMapping("/register")
    public String userRegister(HttpServletRequest req) {
//        System.out.println(sessionManager.getAllSession(req));

        return "content/user/register/userRegister";
    }

    @PostMapping("/register")
    public String userRegisterDo(User user, MultipartFile profileImage) throws Exception {
        userService.userRegister(user, profileImage);
        return "redirect:/user/login";
    }
}
