package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Iotman;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)网关管理 服务接口
//generate by redcloud,2020-07-07 10:18:15
public interface IotmanService {
//@param id 主键 
//@return 实例对象Iotman 
Iotman queryById(Long id);
//@param jsonObj 调用参数 
//@return 实例对象Iotman 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Iotman 
void updateInst(@Param("jsonObj") JSONObject jsonObj);
//@param jsonObj 过滤条件查询 
//@return 实例对象Iotman 
List<Map> iotmanList(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
}
