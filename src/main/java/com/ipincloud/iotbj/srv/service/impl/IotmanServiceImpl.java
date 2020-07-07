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
import com.ipincloud.iotbj.srv.domain.Iotman;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.IotmanService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Iotman)网关管理 服务实现类
//generate by redcloud,2020-07-08 01:57:14
@Service("IotmanService")
public class IotmanServiceImpl implements IotmanService {
    @Resource
    private IotmanDao iotmanDao;

    //@param id 主键 
    //@return 实例对象Iotman 
    @Override 
    public Iotman queryById(Long id){
        return this.iotmanDao.queryById(id); 
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Iotman 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.iotmanDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Iotman 
    @Override 
    public void updateInst(JSONObject jsonObj){
        this.iotmanDao.updateInst(jsonObj); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Iotman 分页 
    @Override
    public Map iotmanList(JSONObject jsonObj){

        int totalRec = this.iotmanDao.countIotmanList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.iotmanDao.iotmanList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer deletesIotmanInst(JSONObject jsonObj){
        Integer delNum1 = this.iotmanDao.deletesInst(jsonObj); 
                return delNum1;
    } 

}
