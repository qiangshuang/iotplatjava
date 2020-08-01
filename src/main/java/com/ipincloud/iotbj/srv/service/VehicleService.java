package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Vehicle;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)车辆白名单 服务接口
//generate by redcloud,2020-07-24 19:59:21
public interface VehicleService {
    //@param id 主键 
    //@return 实例对象Vehicle 
    Vehicle queryById(Long id);
//@param jsonObj 调用参数 
//@return 实例对象Vehicle 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Vehicle 
void updateInst(JSONObject jsonObj);
//@param jsonObj 过滤条件查询 
//@return 实例对象Vehicle 
Map vehicleList(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesVehicleInst(JSONObject jsonObj);
}
