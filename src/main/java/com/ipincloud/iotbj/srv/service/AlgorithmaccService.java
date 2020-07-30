package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Algorithmacc;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)算法接入 服务接口
//generate by redcloud,2020-07-24 19:59:20
public interface AlgorithmaccService {
    //@param id 主键 
    //@return 实例对象Algorithmacc 
    Algorithmacc queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象Algorithmacc 
Map algorithmaccList(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesAlgorithmaccInst(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象Algorithmacc 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Algorithmacc 
void updateInst(JSONObject jsonObj);
}
