package com.qingcheng.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: qingcheng_parent
 * @description:
 * @author: Geng Peng fei
 * @create: 2020-09-21 14:53
 */
@RestController
@RequestMapping("/login")
public class LoginController {


    @GetMapping("/username")
    public Map username(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if ("anonymousUser".equals(username)){
            username="";
        }
        Map map = new HashMap();
        map.put("username",username);
        return map;

    }


}