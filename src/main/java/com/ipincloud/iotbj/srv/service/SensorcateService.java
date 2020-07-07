package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Sensorcate;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)传感器类型 服务接口
//generate by redcloud,2020-07-08 01:57:14
public interface SensorcateService {
    //@param id 主键 
    //@return 实例对象Sensorcate 
    Sensorcate queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象Sensorcate 
Map sensorcateList(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象Sensorcate 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Sensorcate 
void updateInst(JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Sensorcate 
    Integer deleteInst(JSONObject jsonObj);
}
