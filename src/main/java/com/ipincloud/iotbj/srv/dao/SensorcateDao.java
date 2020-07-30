package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Sensorcate;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(SensorcateDao)传感器类型 表数据库访问层
//generate by redcloud,2020-07-24 19:59:20
public interface SensorcateDao {
    //@param id 主键 
    //@return 实例对象Sensorcate 
    Sensorcate queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> sensorcateList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countSensorcateList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Sensorcate 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Sensorcate 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Sensorcate 
    Integer deleteInst(@Param("jsonObj") JSONObject jsonObj);
}
