package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.pojo.system.LoginLog;
import com.qingcheng.service.system.LoginLogService;

import com.qingcheng.util.WebUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Reference
    LoginLogService loginLogService;


    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
         LoginLog loginLog = new LoginLog();
         //当前登录用户
         String loginName = authentication.getName();
         loginLog.setLoginName(loginName);
         loginLog.setLoginTime(new Date());
         //远程ip
        String ip = httpServletRequest.getRemoteAddr();
        loginLog.setIp(ip);
        //地区
        loginLog.setLocation(WebUtil.getCityByIP(ip));
        //浏览器名称
        String agent = httpServletRequest.getHeader("user-agent");
        loginLog.setBrowserName(WebUtil.getBrowserName(agent));
        loginLogService.add(loginLog);
        httpServletRequest.getRequestDispatcher("/main.html").forward(httpServletRequest,httpServletResponse);
    }
}
