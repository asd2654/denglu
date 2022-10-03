package com.example.demo.service;

import com.example.demo.entity.UserBean;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    //将dao层属性注入service层
    @Resource
    private UserMapper userMapper;

    public String LoginInn(String username) {return userMapper.geta(username);}

    public String LoginInw(String username){return userMapper.getw(username);}

    public String LoginIne(String username){return userMapper.gete(username);}

    public void Insert(String username,String password,String email){
        userMapper.saveInfo(username, password,email);
    }
}

