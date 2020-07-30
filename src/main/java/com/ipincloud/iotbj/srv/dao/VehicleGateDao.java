package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.VehicleGate;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(VehicleGateDao)车辆闸机 表数据库访问层
//generate by redcloud,2020-07-24 19:59:21
public interface VehicleGateDao {
    //@param id 主键 
    //@return 实例对象VehicleGate 
    VehicleGate queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> vehicleGateList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countVehicleGateList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象VehicleGate 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象VehicleGate 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
}
