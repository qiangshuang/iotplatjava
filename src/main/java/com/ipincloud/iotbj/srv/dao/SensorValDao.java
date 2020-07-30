package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.SensorVal;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(SensorValDao)数据流 表数据库访问层
//generate by redcloud,2020-07-24 16:15:20
public interface SensorValDao {
    //@param id 主键 
    //@return 实例对象Dataflow 
    SensorVal queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> sensorValList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countSensorValList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Dataflow 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Dataflow 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
}
