 package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.SensorVal;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)数据流 服务接口
//generate by redcloud,2020-07-24 19:59:20
public interface SensorValService {
    //@param id 主键 
    //@return 实例对象Dataflow 
    SensorVal queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象Dataflow 
Map sensorValList(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesDataflowInst(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象Dataflow 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Dataflow 
void updateInst(JSONObject jsonObj);
}
