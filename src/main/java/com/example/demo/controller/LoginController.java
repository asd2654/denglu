package com.example.demo.controller;
import com.example.demo.SHA256.SHA256Util;
import com.example.demo.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Log4j
@Controller
@Component
public class LoginController {
    //将Service注入Web层
    @Resource
    UserService userService;
    @Resource
    private JavaMailSender javaMailSender;


    //实现登录
    @RequestMapping("/login")
    public String show(){
        return "login";
    }
    @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
    public String login(String username,String password) {
        System.out.println(username);
        System.out.println(password);

        String user = userService.LoginInn(username);
        String user1 = userService.LoginIne(username);
        System.out.println(user);


        if (user != null) {
            String SHA256PassWord = SHA256Util.getSecurePassword(password);
            String password1 = userService.LoginInw(username);
            System.out.println(SHA256PassWord);
            System.out.println(password1);
            if (SHA256PassWord.equals(password1)) {
                return "redirect:/index.html";
            } else return "error";

        } else if (user1 != null) {
            String SHA256PassWord = SHA256Util.getSecurePassword(password);
            String password1 = userService.LoginInw(username);
            System.out.println(SHA256PassWord);
            System.out.println(password1);
            if (SHA256PassWord.equals(password1)) {
                return "redirect:/index.html";
            } else return "error";

        }
   else return "error";
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
    public String signUp(String username,String password,String email){
        String SHA256PassWord = SHA256Util.getSecurePassword(password);
      userService.Insert(username, SHA256PassWord,email);
       SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
      simpleMailMessage.setFrom("2654561092@qq.com");
       simpleMailMessage.setTo(email);
     simpleMailMessage.setSubject("注册");
        simpleMailMessage.setText("恭喜注册成功");
       javaMailSender.send(simpleMailMessage);

        return "success";

    }

}
