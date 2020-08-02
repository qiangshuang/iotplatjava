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
import com.ipincloud.iotbj.srv.domain.SensorTrigger;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.SensorTriggerService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(SensorTrigger)触发器管理 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("SensorTriggerService")
public class SensorTriggerServiceImpl implements SensorTriggerService {
    @Resource
    private SensorTriggerDao sensorTriggerDao;

    //@param id 主键 
    //@return 实例对象SensorTrigger 
    @Override 
    public SensorTrigger queryById(Long id){
        return this.sensorTriggerDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询SensorTrigger 分页 
    @Override
    public Map sensorTriggerList(JSONObject jsonObj){

        int totalRec = this.sensorTriggerDao.countSensorTriggerList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.sensorTriggerDao.sensorTriggerList(jsonObj);
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
    public Integer deletesSensorTriggerInst(JSONObject jsonObj){
        Integer delNum1 = this.sensorTriggerDao.deletesInst(jsonObj); 
                return delNum1;
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象SensorTrigger 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
            jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,titel,stream_id,cond,dataval,pushway,created,updated,stream_title");
            this.sensorTriggerDao.addInst(jsonObj);
        // jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数SensorTrigger 
    @Override 
    public void updateInst(JSONObject jsonObj){
        jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,titel,stream_id,cond,dataval,pushway,created,updated,stream_title");        this.sensorTriggerDao.updateInst(jsonObj); 
    } 
}
