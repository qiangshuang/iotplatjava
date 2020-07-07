package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Iotman;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)网关管理 服务接口
//generate by redcloud,2020-07-07 23:53:41
public interface IotmanService {
    //@param id 主键 
    //@return 实例对象Iotman 
    Iotman queryById(Long id);
//@param jsonObj 调用参数 
//@return 实例对象Iotman 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Iotman 
void updateInst(JSONObject jsonObj);
//@param jsonObj 过滤条件查询 
//@return 实例对象Iotman 
Map iotmanList(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesIotmanInst(JSONObject jsonObj);
}
