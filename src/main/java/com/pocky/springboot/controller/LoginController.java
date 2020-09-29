package com.pocky.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author pocky
 * @date 2020/09/21/0021
 */
@Controller
public class LoginController {


    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //登陆成功
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } else{
            map.put("msg","用户名密码错误");
            return "login";
        }
    }
}
