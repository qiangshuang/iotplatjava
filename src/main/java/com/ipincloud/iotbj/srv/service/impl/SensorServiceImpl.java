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
import com.ipincloud.iotbj.srv.domain.Sensor;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.SensorService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Sensor)传感器管理 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("SensorService")
public class SensorServiceImpl implements SensorService {
    @Resource
    private SensorDao sensorDao;

    //@param id 主键 
    //@return 实例对象Sensor 
    @Override 
    public Sensor queryById(Long id){
        return this.sensorDao.queryById(id); 
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Sensor 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
            jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,titel,product_ids,category_id,firm_id,region_id,region_title,stream_id,ipandport,equipment,accesscode,site,created,updated,equ_id,steam_title,devcode");
            this.sensorDao.addInst(jsonObj);
        // jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Sensor 
    @Override 
    public void updateInst(JSONObject jsonObj){
        jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,titel,product_ids,category_id,firm_id,region_id,region_title,stream_id,ipandport,equipment,accesscode,site,created,updated,equ_id,steam_title,devcode");        this.sensorDao.updateInst(jsonObj); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Sensor 分页 
    @Override
    public Map sensorList(JSONObject jsonObj){

        int totalRec = this.sensorDao.countSensorList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.sensorDao.sensorList(jsonObj);
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
    public Integer deletesSensorInst(JSONObject jsonObj){
        Integer delNum1 = this.sensorDao.deletesInst(jsonObj); 
                return delNum1;
    } 
}
