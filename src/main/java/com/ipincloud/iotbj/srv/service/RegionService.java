package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Region;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)区域 服务接口
//generate by redcloud,2020-07-07 23:53:41
public interface RegionService {
    //@param id 主键 
    //@return 实例对象Region 
    Region queryById(Long id);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesRegionInst(JSONObject jsonObj);
//@param jsonObj 过滤条件等 
//@return 实例对象Region 
List<Map> regionTree(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象Region 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Region 
void updateInst(JSONObject jsonObj);
}
