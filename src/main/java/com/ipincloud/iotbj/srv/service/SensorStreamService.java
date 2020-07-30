package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.SensorStream;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)数据流 服务接口
//generate by redcloud,2020-07-24 16:15:21
public interface SensorStreamService {
    //@param id 主键 
    //@return 实例对象SensorStream 
    SensorStream queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象SensorStream 
Map sensorStreamList(JSONObject jsonObj);
}
