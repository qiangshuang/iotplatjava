package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.VehicleGate;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)车辆闸机 服务接口
//generate by redcloud,2020-07-24 19:59:21
public interface VehicleGateService {
    //@param id 主键 
    //@return 实例对象VehicleGate 
    VehicleGate queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象VehicleGate 
Map vehicleGateList(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesVehicleGateInst(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数VehicleGate 
void updateInst(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象VehicleGate 
JSONObject addInst( JSONObject jsonObj);
}
