package com.ipincloud.iotbj.sys.controller;


import com.ipincloud.iotbj.sys.domain.ResponseBean;
import com.ipincloud.iotbj.sys.messinfo.MessageInfo;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    // 捕捉shiro的异常
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseBean handle401(ShiroException e) {

        String exMess = MessageInfo.exceptionInfo(e);
        logger.debug("ShiroException:" + exMess);

        return new ResponseBean(200, "FAILED", "用户未登录", null);
    }

    // 捕捉UnauthorizedException
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseBean handle401() {
        logger.debug("401:用户未登录.");
        return new ResponseBean(200, "FAILED", "用户未登录.", null);
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean globalException(HttpServletRequest request, Throwable ex) {
        String exMess = ex.getMessage();
        logger.debug("ShiroException:" + exMess);
        return new ResponseBean(200, "FAILED", "操作失败:" + exMess, null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}