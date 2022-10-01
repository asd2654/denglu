package com.example.demo.controller;

import com.example.demo.SHA256.SHA256Util;
import com.example.demo.entity.UserBean;
import com.example.demo.md5.MD5util;
import com.example.demo.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Log4j
@Controller
public class LoginController {
    //将Service注入Web层
    @Resource
    UserService userService;

    @Resource
    private SHA256Util sha256Util;

    //实现登录
    @RequestMapping("/login")
    public String show(){
        return "login";
    }
    @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
    public String login(String username,String password){

        String SHA256PassWord = sha256Util.getSecurePassword(password);


        UserBean userBean = userService.LoginIn(username, SHA256PassWord);

        System.out.println(username);
        System.out.println(SHA256PassWord);

        if(userBean!=null){
            return "redirect:/index.html";
        }else {
            return "error";
        }
    }



    @RequestMapping("/index.html")
    public String mainPage() {
        return "index";
    }

    @RequestMapping("/signup")
    public String disp() {
        return "signup";
    }



    //实现注册功能

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String signUp(String username,String password){


        String SHA256PassWord = sha256Util.getSecurePassword(password);

        userService.Insert(username, SHA256PassWord);

        return "success";

    }

}
