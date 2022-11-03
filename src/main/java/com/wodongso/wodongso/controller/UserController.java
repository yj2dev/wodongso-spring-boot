package com.wodongso.wodongso.controller;

import com.wodongso.wodongso.entity.User;
import com.wodongso.wodongso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/register")
    public String userRegister(){
        return "userRegister";
    }

    @PostMapping ("/user/registerdo")
    @ResponseBody
    public String userRegisterDo(User user){
        System.out.println(user.getMajor());
        System.out.println(user.getClassOf());
       userService.userRegister(user);
        return null;
    }

}
