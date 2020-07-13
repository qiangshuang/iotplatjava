package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Dataflow;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)数据流 服务接口
//generate by redcloud,2020-07-07 10:18:15
public interface DataflowService {
//@param id 主键 
//@return 实例对象Dataflow 
Dataflow queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象Dataflow 
List<Map> dataflowList(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象Dataflow 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Dataflow 
void updateInst(@Param("jsonObj") JSONObject jsonObj);
}
