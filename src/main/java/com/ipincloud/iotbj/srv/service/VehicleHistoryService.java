package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.VehicleHistory;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)车辆进出记录 服务接口
//generate by redcloud,2020-07-24 19:59:21
public interface VehicleHistoryService {
    //@param id 主键 
    //@return 实例对象VehicleHistory 
    VehicleHistory queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象VehicleHistory 
Map vehicleHistoryList(JSONObject jsonObj);
}
