package com.ipincloud.iotbj.sys.config;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ipincloud.iotbj.sys.domain.ResponseBean;
import java.io.PrintWriter;
import com.alibaba.fastjson.JSON;

@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter{

    
	/**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        // HttpServletRequest req = (HttpServletRequest) request;
        // String servletPath = req.getServletPath();
        
        // if (servletPath == "/login" && req.getMethod() == "POST" ){
        //     return true; 
        // }
        // return false;
        HttpServletRequest req = (HttpServletRequest) request;

        String authorization = req.getHeader("Authorization");

        return authorization != null;
        
    }

    /**
     *
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // // String authorization = httpServletRequest.getHeader("Authorization");
        // // log.info("判断用户是否想要登录x：{}",authorization);
        // JWTToken token = new JWTToken("");
        // // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        // getSubject(request, response).login(token);
        // // 如果没有抛出异常则代表登入成功，返回true
        // return true;

        HttpServletRequest req = (HttpServletRequest) request;
        String header = req.getHeader("Authorization");

        JWTToken token = new JWTToken(header);

        getSubject(request, response).login(token);

        return true;

    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        if(servletPath == null){
            this.response401(request, response);
            return false;
        }
        if ( "/".equals(servletPath) || servletPath.startsWith("/captcha")  || servletPath.startsWith("/login") ||
            servletPath.startsWith("/logout") ||servletPath.startsWith("/static") || servletPath.startsWith("/401") || 
            servletPath.startsWith("/userselfinfo") ||servletPath.startsWith("/hyupload") ||servletPath.startsWith("/hydownload") ||
           servletPath.startsWith("/404")|| "/favicon.ico".equals(servletPath) ){

            return true;
        }else if (isLoginAttempt(request, response)) { //登录尝试
            
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                this.response401(request, response);
            }
            return false;
        }else{ //login check,permit check at controlller...
            
            return true;//8.2不在检查。
            // Subject currentUser = SecurityUtils.getSubject();
            // if(currentUser. isAuthenticated()){
            //     return true;
            // }else{
            //     this.response401(request, response);
            //     return false;
            // }
        }
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    // move to controller ...
    private void responseNoPermit(ServletRequest req, ServletResponse resp) {
        
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) resp;
        try {

            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");

            out = res.getWriter();

            ResponseBean responseBean = new ResponseBean(200,"FAILED", "用户没有权限访问该服务", null);
            out.println(JSON.toJSONString(responseBean));

        } catch (Exception e) {

        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }
    /**
     * 将非法请求跳转到 /401
     */
    private void response401(ServletRequest req, ServletResponse resp) {
        
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) resp;
        try {

            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");

            out = res.getWriter();

            ResponseBean responseBean = new ResponseBean(200,"FAILED", "用户未登录", null);
            out.println(JSON.toJSONString(responseBean));

        } catch (Exception e) {

        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }
}
