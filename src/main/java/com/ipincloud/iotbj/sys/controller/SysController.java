
package com.ipincloud.iotbj.sys.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
// import com.google.common.collect.Maps;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.ipincloud.iotbj.sys.messinfo.MessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.ipincloud.iotbj.sys.domain.ResponseBean;
import com.ipincloud.iotbj.sys.config.JWTUtil;
import com.ipincloud.iotbj.sys.config.JWTToken;
import com.ipincloud.iotbj.utils.FileUtils;
import com.ipincloud.iotbj.utils.RsaUtils;

import com.ipincloud.iotbj.srv.domain.User;
import com.ipincloud.iotbj.srv.domain.Page;
import com.ipincloud.iotbj.srv.domain.Btn;
import com.ipincloud.iotbj.srv.service.UserService;
import com.ipincloud.iotbj.srv.service.PageService;
import com.ipincloud.iotbj.srv.service.BtnService;


@RestController
public class SysController {
    
	@Value("${keyfile.filePath}")
    private String priKeyPath;

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private static String priKeyStr;
	@Autowired
	private UserService userService;
	@Autowired
	private BtnService btnService;
    @Autowired
    private PageService pageService;

	@PostMapping("/login")
	
	public Object login(@RequestBody String bodyStr,
							  HttpServletResponse response)  {

		if (priKeyStr == null ||  "".equals(priKeyStr) ) {
			priKeyStr = FileUtils.readKeyFile(priKeyPath);
		}
		if (priKeyStr == null || "".equals(priKeyStr) ) {
			logger.error("error","key file path is not exist or read error");
		}
        
        bodyStr = bodyStr.substring(1,bodyStr.length()-1);
        
        String decryptStr ="";
        try{
             decryptStr = RsaUtils.decrypt(bodyStr,priKeyStr);
        }catch(Exception e){

            String exMess = MessageInfo.exceptionInfo(e);
            logger.debug("Ex:"+ exMess);
            return new ResponseBean(200,"FAILED", "登录不成功","");
        }
		

		String[] parasArr = decryptStr.split(",");
		if (parasArr == null || parasArr.length < 4){

            logger.debug("Data:获取的参数或者参数个数不正确。");
			return new ResponseBean(200,"FAILED", "登录不成功","");
		}

		String  userName = parasArr[2];
    	String  pwdSha1 = parasArr[3];

    	if (userName == null || userName.length() < 6 || userName.length() > 20) {
            logger.debug("Data:用户名长度不正确:"+userName.length());
    		return new ResponseBean(200,"FAILED", "登录不成功","");
    	}

    	User user = userService.queryByUsername(userName);

    	if (user == null ){
            logger.debug("Data:通过UserName未找到用户:"+userName);
    		return new ResponseBean(200,"FAILED", "登录不成功","");
    	}

    	if ( ! user.getPwd().equals(pwdSha1) ) {
            logger.debug("Data:密码不一致para:"+pwdSha1+":dbpwd:"+user.getPwd());
    		return new ResponseBean(200,"FAILED", "输入用户名或密码不正确","");
    	}

    	String btnStr = btnService.queryByUserId(user.getId());
        List<Map> pages = pageService.queryAllByUser(user.getId());

        String token ="";
        try{
            token = JWTUtil.sign(user.getId(), user.getUserName());
        }catch(Exception e){
            String exMess = MessageInfo.exceptionInfo(e);
            logger.debug("Ex:"+ exMess);
            return new ResponseBean(200,"FAILED", "登录不成功","");
        }

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(new JWTToken(token) );
        // try{
        //     currentUser.checkPermission("/login");
        // }catch(Exception ex){
            
        // }
        

        response.setHeader("token", token);

        Map retMap = new HashMap();
        user.setUserName("");
        user.setPwd("");
        retMap.put("menuList",pages);
        retMap.put("userInfo",user);
        retMap.put("org", null);
        retMap.put("btnList",new ArrayList());

        ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "登录成功",retMap);
        retResponseBean.setAuthorization(token);
        return  JSON.toJSONString(retResponseBean);
	}

	@PostMapping("/userselfinfo")
    public Object userselfinfo(@RequestBody String bodyStr,
                              HttpServletResponse response)  {

        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isPermitted("/userselfinfo")){
            ResponseBean retResponseBean = new ResponseBean(200,"FAILED", "权限不足,请与管理员联系",null);
            return JSON.toJSONString(retResponseBean);
        }
        long userId = JWTUtil.getUserId(currentUser.getPrincipals().toString());
        if (userId < 1) {
            logger.debug("Data:获取用户ID不成功.");
            return new ResponseBean(200,"FAILED", "用户数据出现错误","");
        }
        
        User user = userService.queryById(userId);

        String btnStr = btnService.queryByUserId(userId);
        List<Map> pages = pageService.queryAllByUser(user.getId());

        Map retMap = new HashMap();

        user.setUserName("");
        user.setPwd("");
        retMap.put("menuList",pages);
        retMap.put("userInfo",user);
        retMap.put("org", null);
        retMap.put("btnList",new ArrayList());

        return new ResponseBean(200,"SUCCESS", "登录成功",retMap);
    }

    @PostMapping("/logout")
    public Object logout(@RequestBody String bodyStr,
                              HttpServletResponse response)  {

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();

        response.setHeader("Authorization", "");
        ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "登出成功",null);
        retResponseBean.setAuthorization("");
        return  JSON.toJSONString(retResponseBean);
    }


}
