
package com.ipincloud.iotbj.sys.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
// import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import com.ipincloud.iotbj.sys.domain.ResponseBean;
import com.ipincloud.iotbj.sys.config.JWTUtil;
import com.ipincloud.iotbj.sys.config.JWTToken;
import com.ipincloud.iotbj.utils.FileUtils;
import com.ipincloud.iotbj.utils.RsaUtils;
import com.ipincloud.iotbj.utils.ParaUtils;

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
        if (bodyStr.length()  < 2 ){
            logger.debug("Data:获取的参数或者参数个数不正确:"+ bodyStr);
            return new ResponseBean(200,"FAILED", "登录不成功","");
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
        

        response.setHeader("Authorization", token);

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
        // DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        // IpincloudRealm ipincloudRealm = (IpincloudRealm) securityManager.getRealms().iterator().next();
        // PrincipalCollection principals = subject.getPrincipals();
        // ipincloudRealm.clearCache(principals);

        currentUser.logout();

        response.setHeader("Authorization", "");
        ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "登出成功",null);
        retResponseBean.setAuthorization("");
        return  JSON.toJSONString(retResponseBean);
    }
    @PostMapping("/userselfup")
    public Object userselfup(@RequestBody JSONObject jsonObject,
                              HttpServletResponse response)  {

        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isPermitted("/userselfup")){
            ResponseBean retResponseBean = new ResponseBean(200,"FAILED", "权限不足,请与管理员联系",null);
            return JSON.toJSONString(retResponseBean);
        }
        long userId = JWTUtil.getUserId(currentUser.getPrincipals().toString());
        if (userId < 1) {
            logger.debug("Data:获取用户ID不成功.");
            return new ResponseBean(200,"FAILED", "用户数据出现错误","");
        }
        //
        String missCols = ParaUtils.hasAllColValInt(jsonObject,"id");
        if (missCols.length() >0 ){
            return new ResponseBean(200,"FAILED", "更新参数缺少ID","");
        }
        missCols = ParaUtils.notHaveColVal(jsonObject,"user_name,pwd");
        if (missCols.length() >0 ){
            return new ResponseBean(200,"FAILED", "不能更新账号和密码","");
        }
        
        Object idVal = jsonObject.get("id");
        if ( ! idVal.equals(userId ) ){
            return new ResponseBean(200,"FAILED", "不能更新其他人员资料","");
        }
        userService.updateInst(jsonObject);

        // 

        return new ResponseBean(200,"SUCCESS", "更新成功",jsonObject);
    }
    @PostMapping("/userselfpwd")
    public Object userselfpwd(@RequestBody String bodyStr,
                              HttpServletResponse response)  {

        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isPermitted("/userselfpwd")){
            ResponseBean retResponseBean = new ResponseBean(200,"FAILED", "权限不足,请与管理员联系",null);
            return JSON.toJSONString(retResponseBean);
        }
        long userId = JWTUtil.getUserId(currentUser.getPrincipals().toString());
        if (userId < 1) {
            logger.debug("Data:获取用户ID不成功.");
            return new ResponseBean(200,"FAILED", "用户数据出现错误","");
        }
        
        if (bodyStr.length()  < 2 ){
            logger.debug("Data:获取的参数或者参数个数不正确:"+ bodyStr);
            return new ResponseBean(200,"FAILED", "登录不成功","");
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
        if (parasArr == null || parasArr.length < 3){

            logger.debug("Data:获取的参数或者参数个数不正确。");
            return new ResponseBean(200,"FAILED", "登录不成功","");
        }

        
        String  pwdSha1 = parasArr[2];
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", userId);
        jsonObject.put("pwd", pwdSha1);
        
        userService.updateInst(jsonObject);
        return new ResponseBean(200,"SUCCESS", "更新密码成功","");

    }
    @PostMapping("/useraccount")
    public Object userselfinfo(@RequestBody JSONObject jsonObject,
                              HttpServletResponse response)  {

        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isPermitted("/useraccount")){
            ResponseBean retResponseBean = new ResponseBean(200,"FAILED", "权限不足,请与管理员联系",null);
            return JSON.toJSONString(retResponseBean);
        }
        long userId = JWTUtil.getUserId(currentUser.getPrincipals().toString());
        if (userId < 1) {
            logger.debug("Data:获取用户ID不成功.");
            return new ResponseBean(200,"FAILED", "用户数据出现错误","");
        }
        
        String userStr = jsonObject.get("user").toString();
        if (userStr == null || userStr.length() < 1 ){
            logger.debug("Data:没有参数字符串:"+userStr);
            return new ResponseBean(200,"FAILED", "用户数据出现错误","");
        }
        String decryptStr ="";
        try{
             decryptStr = RsaUtils.decrypt(userStr,priKeyStr);
        }catch(Exception e){

            String exMess = MessageInfo.exceptionInfo(e);
            logger.debug("Ex:"+ exMess);
            return new ResponseBean(200,"FAILED", "设置账号不成功","");
        }
        

        String[] parasArr = decryptStr.split(",");
        if (parasArr == null || parasArr.length < 5){

            logger.debug("Data:获取的参数或者参数个数不正确:"+parasArr.toString() );
            return new ResponseBean(200,"FAILED", "登录不成功","");
        }

        String userName = parasArr[2];
        String pwdSha1 = parasArr[3];
        Long upId = Long.parseLong(parasArr[4]);

        if (upId < 1 || 
            ( userName!=null && ( 
                (userName.length()> 0 && userName.length() <6)|| userName.length()>20  )
            )
            ){
            return new ResponseBean(200,"FAILED", "设置账号密码参数不正确","");
        }

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("user_id", upId);
        if (userName != null && userName.length()> 0 ){
            jsonObject2.put("user_name",userName);
        }
        jsonObject2.put("pwd", pwdSha1);
        
        userService.updateInst(jsonObject2);
        return new ResponseBean(200,"SUCCESS", "用户账号密码设置成功","");
    }
    @PostMapping("/hyupload")
    
    public Object hyupload(HttpServletRequest request,
                              HttpServletResponse response)  {

        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isPermitted("/hyupload")){
            ResponseBean retResponseBean = new ResponseBean(200,"FAILED", "权限不足,请与管理员联系",null);
            return JSON.toJSONString(retResponseBean);
        }
        

        long userId = JWTUtil.getUserId(currentUser.getPrincipals().toString());
        if (userId < 1) {
            logger.debug("Data:获取用户ID不成功.");
            return new ResponseBean(200,"FAILED", "用户数据出现错误","");
        }
        String relateType = request.getParameter("relateType");

        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        //单文件上传
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        //List<MultipartFile> files = multipartRequest.getFiles("files");
        String originFileName = file.getOriginalFilename();
        String suffixName = originFileName.substring(originFileName.lastIndexOf("."));

        String uuId =  UUID.randomUUID().toString();
        Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd hh:mm:ss").format(date);
        
        String runPath = request.getRealPath("");
        String fullfilPath = runPath+String.format("/%d/%s/%s/%s.%s",userId,relateType,dateStr,uuId,suffixName);

        // 文件对象

        File dest = new File( fullfilPath );
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 保存到服务器中
            file.transferTo(dest);
            String retStr = String.format("%s;%s;0;%d;;%d",originFileName,fullfilPath,userId,System.currentTimeMillis());
            return new ResponseBean(200,"SUCCESS", "文件上传成功",retStr);
        } catch (Exception e) {
            String exMess = MessageInfo.exceptionInfo(e);
            logger.debug("Ex:"+ exMess);
            return new ResponseBean(200,"FAILED", "文件上传不成功","");
        }

        


        
    }
    @GetMapping("/hydownload")
    public Object userselfinfo(HttpServletRequest request,
                              HttpServletResponse response)  {

        String relateType = request.getParameter("relateType");
        String filename = request.getParameter("filename");
        String authorization = request.getParameter("Authorization");
        String savefilename = request.getParameter("savefilename");

        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isPermitted("/hyupload")){
            ResponseBean retResponseBean = new ResponseBean(200,"FAILED", "权限不足,请与管理员联系",null);
            return JSON.toJSONString(retResponseBean);
        }
        long userId = JWTUtil.getUserId(currentUser.getPrincipals().toString());
        if (userId < 1) {
            logger.debug("Data:获取用户ID不成功.");
            return new ResponseBean(200,"FAILED", "用户数据出现错误","");
        }
        String runPath = request.getRealPath("");
        String fullfilPath = runPath+filename;
        if (savefilename == null || savefilename.length() < 1){
            if( fullfilPath.lastIndexOf("\\") > -1){
                savefilename = fullfilPath.substring(fullfilPath.lastIndexOf("\\")+1);
            }else{
                savefilename = fullfilPath.substring(fullfilPath.lastIndexOf("/")+1);
            }
              
        }

        File file = new File( fullfilPath );
        if ( file.exists() ){
            // 输入对象
            FileInputStream fis = null;
            // 设置相关格式
            response.setContentType("application/force-download");
            // 设置下载后的文件名以及header
            response.addHeader("Content-disposition", "attachment;fileName=" + filename);
            // 创建输出对象
            OutputStream os = null;
            byte[] buf = new byte[1024];

            try{
                fis = new FileInputStream(file);
                os = response.getOutputStream();
                int len = 0;
                while((len = fis.read(buf)) != -1) {
                    os.write(buf, 0, len);
                }
            }catch(Exception e){
                String exMess = MessageInfo.exceptionInfo(e);
                logger.debug("Ex:文件下载:"+ exMess);
                
            }finally{
                os.close();
                fis.close();

            }
            
        }
         
    }

}
