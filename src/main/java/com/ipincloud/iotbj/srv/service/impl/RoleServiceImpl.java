package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Role;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.RoleService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Role)角色 服务实现类
//generate by redcloud,2020-07-08 01:57:14
@Service("RoleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    //@param id 主键 
    //@return 实例对象Role 
    @Override 
    public Role queryById(Long id){
        return this.roleDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Role 分页 
    @Override
    public Map roleList(JSONObject jsonObj){

        int totalRec = this.roleDao.countRoleList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.roleDao.roleList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 调用参数 
    //@return 实例对象Role 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.roleDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Role 
    @Override 
    public void updateInst(JSONObject jsonObj){
        this.roleDao.updateInst(jsonObj); 
    } 

    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer deletesRoleInst(JSONObject jsonObj){
        Integer delNum1 = this.roleDao.deletesInst(jsonObj); 
                return delNum1;
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象Role 查询
    @Override
    public List<Map> roleQuery(JSONObject jsonObj){

        return this.roleDao.roleQuery(jsonObj);
        
    }
    
    //@param jsonObj 过滤条件等 
    //@return 实例对象Role 
    @Override 
    public Map roleUserRoleMmlist(JSONObject jsonObj){
         int totalRec = this.roleDao.countRoleUserRoleMmlist(jsonObj); 
        jsonObj.put("totalRec",totalRec);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.roleDao.roleUserRoleMmlist(jsonObj);

        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
    
}
