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
import com.ipincloud.iotbj.srv.domain.SensorAlarm;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.SensorAlarmService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Sensor)传感器管理 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("SensorAlarmService")
public class SensorAlarmServiceImpl implements SensorAlarmService {
    @Resource
    private SensorAlarmDao sensorAlarmDao;

    //@param id 主键 
    //@return 实例对象Sensor 
    @Override 
    public SensorAlarm queryById(Long id){
        return this.sensorAlarmDao.queryById(id); 
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Sensor 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
            jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,sensor_id,sensor_title,region_id,region_title,sensor_categary,dataval,standardval,grade,alarmtiem,state,created,updated");
            this.sensorAlarmDao.addInst(jsonObj);
        // jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Sensor 
    @Override 
    public void updateInst(JSONObject jsonObj){
        jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,sensor_id,sensor_title,region_id,region_title,sensor_categary,dataval,standardval,grade,alarmtiem,state,created,updated");        
        this.sensorAlarmDao.updateInst(jsonObj); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Sensor 分页 
    @Override
    public Map sensorAlarmList(JSONObject jsonObj){

        int totalRec = this.sensorAlarmDao.countSensorAlarmList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.sensorAlarmDao.sensorAlarmList(jsonObj);
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
    public Integer deletesSensorAlarmInst(JSONObject jsonObj){
        Integer delNum1 = this.sensorAlarmDao.deletesInst(jsonObj); 
                return delNum1;
    } 
}
