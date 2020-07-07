package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Baseman;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)基站管理 服务接口
//generate by redcloud,2020-07-08 01:57:14
public interface BasemanService {
    //@param id 主键 
    //@return 实例对象Baseman 
    Baseman queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象Baseman 
Map basemanList(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesBasemanInst(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象Baseman 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Baseman 
void updateInst(JSONObject jsonObj);
}
