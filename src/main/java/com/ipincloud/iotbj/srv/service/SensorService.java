package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Sensor;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)传感器管理 服务接口
//generate by redcloud,2020-07-24 19:59:20
public interface SensorService {
    //@param id 主键 
    //@return 实例对象Sensor 
    Sensor queryById(Long id);
//@param jsonObj 调用参数 
//@return 实例对象Sensor 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Sensor 
void updateInst(JSONObject jsonObj);
//@param jsonObj 过滤条件查询 
//@return 实例对象Sensor 
Map sensorList(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesSensorInst(JSONObject jsonObj);
}
