package com.example.demo.controller;

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

    public LoginController() throws NoSuchAlgorithmException {
    }

    //实现登录
    @RequestMapping("/login")
    public String show() {
        return "login";
    }

    @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
    public String login(String username,String password){

        //用用户名生成盐值
        int numbSalt = username.hashCode();

        String salt = String.valueOf(numbSalt);

        //加盐
        String newPassWord = salt + password;

        //得出密文
        String md5PassWord = MD5util.getMD5(newPassWord);

        UserBean userBean = userService.LoginIn(username, md5PassWord);
        System.out.println(username);
        System.out.println(md5PassWord+"=======");
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

        //用用户名生成盐值
        int numbSalt = username.hashCode();

        String salt = String.valueOf(numbSalt);

        //加盐
        String newPassWord = salt + password;

        //得出密文
        String md5PassWord = MD5util.getMD5(newPassWord);

        userService.Insert(username, md5PassWord);

        return "success";



    }

}
