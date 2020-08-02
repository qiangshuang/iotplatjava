package com.ipincloud.iotbj.srv.dao;

import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Vehicle;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

//(VehicleDao)车辆白名单 表数据库访问层
//generate by redcloud,2020-07-23 11:43:18
public interface VehicleDao {
    //@param id 主键
    //@return 实例对象Vehicle
    Vehicle queryById(Long id);

    //@param jsonObj 过滤条件等
    //@return 实例对象Vehicle
    int addInst(@Param("jsonObj") JSONObject jsonObj);

    //@param jsonObj 过滤条件等
    //@return 实例对象Vehicle
    int updateInst(@Param("jsonObj") JSONObject jsonObj);

    //@param jsonObj 过滤条件等
    //@return 实例对象
    List<Map> vehicleList(@Param("jsonObj") JSONObject jsonObj);

    //@param jsonObj 过滤条件等
    //@return 总条数list
    Integer countVehicleList(@Param("jsonObj") JSONObject jsonObj);

    //@param jsonObj 过滤条件等
    //@return 影响记录数
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);

    List<String> queryByIds(@Param("ids") List<Long> ids);
}
