package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.SensorTrigger;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)触发器管理 服务接口
//generate by redcloud,2020-07-24 19:59:20
public interface SensorTriggerService {
    //@param id 主键 
    //@return 实例对象SensorTrigger 
    SensorTrigger queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象SensorTrigger 
Map sensorTriggerList(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesSensorTriggerInst(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象SensorTrigger 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数SensorTrigger 
void updateInst(JSONObject jsonObj);
}
