package com.ipincloud.iotbj.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;

@RestController
public class MenuController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    @GetMapping("/menu")
    public String onMenuReq(){
        String code=request.getParameter("code");
        System.out.println("code:"+code);
        System.out.println("header cookie:"+ request.getHeader("cookie"));

        HttpSession httpSession=request.getSession();
        if(httpSession!=null &&  httpSession.getAttribute("code")!=null && httpSession.getAttribute("code").equals(code)){
            return "code:"+code;
        }
        if(httpSession!=null){
            httpSession.setAttribute("code","");
            //todo clear other Attributes
        }
        try {
            String redirect_url="http://10.69.212.11:8189/onauth?redirect_url="+ URLEncoder.encode("http://10.69.212.11:8189/menu");
            System.out.println("http://10.69.206.42:81/?exitFlag=false&redirect_url="+ Base64.getEncoder().encodeToString(redirect_url.getBytes()));
            response.sendRedirect("http://10.69.206.42:81/?exitFlag=false&redirect_url="+ Base64.getEncoder().encodeToString(redirect_url.getBytes()));
            return null;
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return "hello";
    }

    @PostMapping("/onauth")
    public void onAuth(){
        System.out.println(request.getParameter("redirect_url"));
        System.out.println(request.getParameter("code"));
        System.out.println(request.getParameter("iamAccessToken"));
        //todo 根據iamAccessToken獲取用戶信息
        HttpSession httpSession=request.getSession();
        httpSession.setAttribute("code",request.getParameter("code"));
        try {
            response.sendRedirect(request.getParameter("redirect_url")+"?code="+request.getParameter("code"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
