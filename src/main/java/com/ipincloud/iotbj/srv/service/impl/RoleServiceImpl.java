package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Role;
import com.ipincloud.iotbj.srv.dao.RoleDao;
import com.ipincloud.iotbj.srv.service.RoleService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Role)角色 服务实现类
//generate by redcloud,2020-07-07 10:18:16
@Service("RoleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;
    //@param id 主键 
    //@return 实例对象Role 
    @Override 
    public Role queryById(Long id){
        this.roleDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Role 分页 
    @Override
    public List<Map> roleList(JSONObject jsonObj){

        int totalRec = this.countRoleList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.roleDao.roleList(jsonObj)
        list<Map> retMap = new HashMap();
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
        return this.roleDao.updateInst(jsonObj); 
    } 

    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer deletesInst(JSONObject jsonObj){
        Integer delNum1 = this.roleDao.deletesInst(jsonObj); 
        Integer delNum2 = new com.ipincloud.iotbj.srv.dao.Dao().deletesInst(jsonObj); 
        return delNum1+delNum2;
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
    public List<Map> roleUserRoleMmlist JSONObject jsonObj){
         int totalRec = this.roleDao.countRoleUserRoleMmlist(jsonObj); 
        jsonObj.put("totalRec",totalRec);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec);
        list<Map> pageData = this.roleDao.roleUserRoleMmlist(jsonObj);

        list<Map> retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
    
}
