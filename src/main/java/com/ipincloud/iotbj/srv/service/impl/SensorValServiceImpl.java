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
import com.ipincloud.iotbj.srv.domain.SensorVal;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.SensorValService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(SensorVal)数据流 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("SensorValService")
public class SensorValServiceImpl implements SensorValService {
    @Resource
    private SensorValDao sensorValDao;

    //@param id 主键 
    //@return 实例对象Dataflow 
    @Override 
    public SensorVal queryById(Long id){
        return this.sensorValDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Dataflow 分页 
    @Override
    public Map sensorValList(JSONObject jsonObj){

        int totalRec = this.sensorValDao.countSensorValList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.sensorValDao.sensorValList(jsonObj);
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
    public Integer deletesDataflowInst(JSONObject jsonObj){
        Integer delNum1 = this.sensorValDao.deletesInst(jsonObj); 
                return delNum1;
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Dataflow 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
            jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,dataval,datatitle,company,comsym,equman");
            this.sensorValDao.addInst(jsonObj);
        
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Dataflow 
    @Override 
    public void updateInst(JSONObject jsonObj){
        jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,dataval,datatitle,company,comsym,equman");        
        this.sensorValDao.updateInst(jsonObj); 
    } 
}
