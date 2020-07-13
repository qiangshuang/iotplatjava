package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.UserRole;
import com.ipincloud.iotbj.srv.dao.UserRoleDao;
import com.ipincloud.iotbj.srv.service.UserRoleService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(UserRole) 服务实现类
//generate by redcloud,2020-07-07 10:18:16
@Service("UserRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleDao userRoleDao;
    //@param id 主键 
    //@return 实例对象UserRole 
    @Override 
    public UserRole queryById(Long id){
        this.userRoleDao.queryById(id); 
    } 

    //@param jsonObj 新增数据等 
    //@return JSONObject 查询
    @Override
    public Integer userRoleMmjoin(JSONObject jsonObj){

        return this.userRoleDao.userRoleMmjoin(jsonObj)
        
    }
            
    
    //@param jsonObj 新增数据等 
    //@return JSONObject 查询
    @Override
    public Integer userRoleMmsub(JSONObject jsonObj){

        return this.userRoleDao.userRoleMmsub(jsonObj)
        
    }
    
    
}
