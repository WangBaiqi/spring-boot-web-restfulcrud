package com.pocky.springboot.controller;

import com.pocky.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pocky
 * @date 2020/09/23/0023
 */

@ControllerAdvice
public class MyExceptionHandler {

    //1、浏览器和客户端返回的都是json
    //@ResponseBody
    //@ExceptionHandler(UserNotExistException.class)
    //public Map<String, Object> handleException(Exception e){
    //
    //    Map<String,Object> map = new HashMap<>();
    //    map.put("code", "user.notexist");
    //    map.put("message", e.getMessage());
    //    return map;
    //}
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){

        Map<String,Object> map = new HashMap<>();
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());

        request.setAttribute("ext", map);
        //转发到/error
        return "forward:/error";
    }
}
