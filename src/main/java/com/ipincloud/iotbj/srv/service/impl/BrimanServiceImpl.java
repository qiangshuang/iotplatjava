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
import com.ipincloud.iotbj.srv.domain.Briman;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.BrimanService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Briman)网桥管理 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("BrimanService")
public class BrimanServiceImpl implements BrimanService {
    @Resource
    private BrimanDao brimanDao;

    //@param id 主键 
    //@return 实例对象Briman 
    @Override 
    public Briman queryById(Long id){
        return this.brimanDao.queryById(id); 
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Briman 
    @Override 
    public void updateInst(JSONObject jsonObj){
        jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,title,ipaddress,state,factory,signales");        this.brimanDao.updateInst(jsonObj); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Briman 分页 
    @Override
    public Map brimanList(JSONObject jsonObj){

        int totalRec = this.brimanDao.countBrimanList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.brimanDao.brimanList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 调用参数 
    //@return 实例对象Briman 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
            jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,title,ipaddress,state,factory,signales");
            this.brimanDao.addInst(jsonObj);
        // jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer deletesBrimanInst(JSONObject jsonObj){
        Integer delNum1 = this.brimanDao.deletesInst(jsonObj); 
                return delNum1;
    } 
}
