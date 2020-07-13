package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.RolePage;
import com.ipincloud.iotbj.srv.dao.RolePageDao;
import com.ipincloud.iotbj.srv.service.RolePageService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(RolePage) 服务实现类
//generate by redcloud,2020-07-07 10:18:16
@Service("RolePageService")
public class RolePageServiceImpl implements RolePageService {
    @Resource
    private RolePageDao rolePageDao;
    //@param id 主键 
    //@return 实例对象RolePage 
    @Override 
    public RolePage queryById(Long id){
        this.rolePageDao.queryById(id); 
    } 

    //@param jsonObj 新增数据等 
    //@return JSONObject 查询
    @Override
    public Integer rolePageMmjoin(JSONObject jsonObj){

        return this.rolePageDao.rolePageMmjoin(jsonObj)
        
    }
            
    
    //@param jsonObj 新增数据等 
    //@return JSONObject 查询
    @Override
    public Integer rolePageMmsub(JSONObject jsonObj){

        return this.rolePageDao.rolePageMmsub(jsonObj)
        
    }
    
    
}
