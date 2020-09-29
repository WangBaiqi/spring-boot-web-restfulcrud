package com.pocky.springboot.component;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author pocky
 * @date 2020/09/23/0023
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {


    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        map.put("company", "atpocky");

        //我们的异常处理器携带的数据
        Map<String,Object> ext = (Map<String,Object>) webRequest.getAttribute("ext", 0);
        map.put("ext", ext);
        return map;
    }
}
