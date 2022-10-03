package com.example.demo.mapper;

import com.example.demo.entity.UserBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Mapper
public interface UserMapper {
    @Select("SELECT username FROM user where username =#{username} ")
    String geta(@Param("username") String username);

    @Select("select username from user where email=#{username}")
    String gete(@Param("username") String username);

    @Select("select password from user where username=#{username}")
    String getw(@Param("username") String username);


    @Insert("insert into user(username,password,email)values(#{username},#{password},#{email})")
    void saveInfo(@Param("username") String username, @Param("password") String password,@Param("email") String email);
}




