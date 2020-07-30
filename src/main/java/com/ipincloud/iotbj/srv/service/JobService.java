package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Job;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-24 19:59:20
public interface JobService {
    //@param id 主键 
    //@return 实例对象Job 
    Job queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象Job 
Map jobList(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象Job 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Job 
void updateInst(JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Job 
    Integer deleteInst(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesJobInst(JSONObject jsonObj);
}
