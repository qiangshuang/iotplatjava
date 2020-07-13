package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Region;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(RegionDao)区域 表数据库访问层
//generate by redcloud,2020-07-07 10:18:16
public interface RegionDao {
    //@param id 主键 
    //@return 实例对象Region 
    Region queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Region 
    List<Map> queryTreeHp(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Region 
    List<Map> queryTreeNp(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Region 
    Long addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Region 
    void updateInst(@Param("jsonObj") JSONObject jsonObj);
}
