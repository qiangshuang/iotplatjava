package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Sensorcate;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)传感器类型 服务接口
//generate by redcloud,2020-07-07 10:18:16
public interface SensorcateService {
//@param id 主键 
//@return 实例对象Sensorcate 
Sensorcate queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象Sensorcate 
List<Map> sensorcateList(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象Sensorcate 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Sensorcate 
void updateInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Sensorcate 
    Integer deleteInst(@Param("jsonObj") JSONObject jsonObj);
}
