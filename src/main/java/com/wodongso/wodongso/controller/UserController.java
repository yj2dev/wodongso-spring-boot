package com.wodongso.wodongso.controller;

import com.wodongso.wodongso.entity.User;
import com.wodongso.wodongso.service.SocietyService;
import com.wodongso.wodongso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SocietyService societyService;

    @GetMapping("/")
    public String root(Model model){

        System.out.println(societyService.societyList());
        model.addAttribute("list", societyService.societyList());
        return "index";

    }


    @GetMapping("/user/register")
    public String userRegister(){
        return "userRegister";
    }

    @GetMapping("/user/login")
    public String userLogin(){
        return "userLogin";
    }

    @PostMapping ("/user/logindo")
    @ResponseBody
    public String userLoginDo(User user){
//        System.out.println('user >> ', user);
        return null;
    }

    @PostMapping ("/user/registerdo")
    @ResponseBody
    public String userRegisterDo(User user){
       userService.userRegister(user);
        return null;
    }

}
