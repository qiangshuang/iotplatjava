package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Dataflow;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)数据流 服务接口
//generate by redcloud,2020-07-08 01:57:14
public interface DataflowService {
    //@param id 主键 
    //@return 实例对象Dataflow 
    Dataflow queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象Dataflow 
Map dataflowList(JSONObject jsonObj);
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
