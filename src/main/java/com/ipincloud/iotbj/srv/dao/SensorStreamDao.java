package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.SensorStream;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(SensorStreamDao)数据流 表数据库访问层
//generate by redcloud,2020-07-24 16:15:20
public interface SensorStreamDao {
    //@param id 主键 
    //@return 实例对象SensorStream 
    SensorStream queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> sensorStreamList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countSensorStreamList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象SensorStream 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象SensorStream 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
}
