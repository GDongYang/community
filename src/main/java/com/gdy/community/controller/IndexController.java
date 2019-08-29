package com.gdy.community.controller;

import com.gdy.community.mapper.UserMapper;
import com.gdy.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String hello(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();

        String token = "";
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";

    }
}
