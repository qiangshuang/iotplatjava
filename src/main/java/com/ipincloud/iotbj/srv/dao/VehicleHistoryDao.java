package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.VehicleHistory;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(VehicleHistoryDao)车辆进出记录 表数据库访问层
//generate by redcloud,2020-07-24 19:59:21
public interface VehicleHistoryDao {
    //@param id 主键 
    //@return 实例对象VehicleHistory 
    VehicleHistory queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> vehicleHistoryList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countVehicleHistoryList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象VehicleHistory 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象VehicleHistory 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
}
