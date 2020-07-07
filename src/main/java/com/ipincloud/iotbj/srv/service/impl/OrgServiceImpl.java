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
import com.ipincloud.iotbj.srv.domain.Org;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.OrgService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Org) 服务实现类
//generate by redcloud,2020-07-08 01:57:14
@Service("OrgService")
public class OrgServiceImpl implements OrgService {
    @Resource
    private OrgDao orgDao;
    @Resource
    private UserDao userDao;
    @Resource
    private UserRoleDao userRoleDao;

    //@param id 主键 
    //@return 实例对象Org 
    @Override 
    public Org queryById(Long id){
        return this.orgDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象Org 树形查询
    @Override
    public List<Map> orgTree(JSONObject jsonObj){

        if (ParaUtils.notHaveColVal(jsonObj,"parent_id") != null && ParaUtils.notHaveColVal(jsonObj,"parent_id").length() > 0){
            return this.orgDao.queryTreeHp(jsonObj);
        }else{
            return this.orgDao.queryTreeNp(jsonObj);
        }
    }
            
            
    //@param jsonObj 调用参数 
    //@return 实例对象Org 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.orgDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Org 
    @Override 
    public void updateInst(JSONObject jsonObj){
        this.orgDao.updateInst(jsonObj); 
    } 

    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer deletesOrgInst(JSONObject jsonObj){
        Integer delNum1 = this.orgDao.deletesInst(jsonObj); 
                return delNum1;
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象Org 查询
    @Override
    public List<Map> orgQuery(JSONObject jsonObj){

        return this.orgDao.orgQuery(jsonObj);
        
    }
    
    //@param jsonObj 过滤条件等 
    //@return 对象查询Org 分页 
    @Override
    public Map orgList(JSONObject jsonObj){

        int totalRec = this.orgDao.countOrgList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.orgDao.orgList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 调用参数 
    //@return jsonObj 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public JSONObject addOrgUserInstAttr( JSONObject jsonObj){
        JSONObject jsonObjFirst = ParaUtils.copyJsonObjectCols(jsonObj,"id,type,parent_id,sort,data_scope,stop,memo,parent_title,title");
        Long genId = this.orgDao.addInst(jsonObjFirst); 
        jsonObj.put("id",genId);
        JSONObject jsonObjSecond = ParaUtils.copyJsonObjectCols(jsonObj,"gender");
        this.userDao.addInst(jsonObjSecond); 
        return jsonObj; 
    } 

    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateOrgUserInstAttr(JSONObject jsonObj){
        JSONObject jsonObjFirst = ParaUtils.copyJsonObjectCols(jsonObj,"title");
        this.orgDao.updateInst(jsonObjFirst); 
        JSONObject jsonObjSecond = ParaUtils.copyJsonObjectCols(jsonObj,"gender");
        this.userDao.updateInst(jsonObjSecond); 
    } 

    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer deletesOrgUserUserRoleInst(JSONObject jsonObj){
        Integer delNum1 = this.orgDao.deletesInst(jsonObj); 
        Integer delNum2=0;
        delNum2 = this.userDao.deletesInst(jsonObj); 
        delNum1 = delNum1+delNum2;
        delNum2 = this.userRoleDao.deletesInst(jsonObj); 
        delNum1 = delNum1+delNum2;
        return delNum1;
    } 

}
