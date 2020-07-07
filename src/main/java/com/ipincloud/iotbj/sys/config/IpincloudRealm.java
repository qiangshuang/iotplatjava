package com.ipincloud.iotbj.sys.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.Cache;
import com.ipincloud.iotbj.srv.domain.User;
import com.ipincloud.iotbj.srv.service.UserService;
import com.ipincloud.iotbj.srv.domain.Btn;
import com.ipincloud.iotbj.srv.service.BtnService;

public class IpincloudRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private BtnService btnService;

    public IpincloudRealm(){
        //整体缓存?
        // super.setName("ipincloudRealm");
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();;
        long userId = JWTUtil.getUserId(principals.toString());
        String userBtns = btnService.queryByUserId(userId);
        Set<String> permission = new HashSet<String>(Arrays.asList(userBtns.split(";")));
        simpleAuthorizationInfo.addStringPermissions(permission);
        
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户正确与否验证，错误抛出异常即可
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        Long userId = JWTUtil.getUserId(token);
        if (userId < 1) {
            throw new AuthenticationException("未登录用户.");
        }
        
        User user = userService.queryById(userId);
        if (user == null) {
            throw new AuthenticationException("用户不存在") ;
        }
        
        if (!JWTUtil.verify(token, user.getId(), user.getUserName())) {
            throw new AuthenticationException("未登录用户!");
        }
        return new SimpleAuthenticationInfo(token, token, "ipincloud_realm");
    }

}