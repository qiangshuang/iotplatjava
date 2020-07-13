package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Baseman;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(BasemanDao)基站管理 表数据库访问层
//generate by redcloud,2020-07-07 10:18:15
public interface BasemanDao {
    //@param id 主键 
    //@return 实例对象Baseman 
    Baseman queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> basemanList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countBasemanList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Baseman 
    Long addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Baseman 
    void updateInst(@Param("jsonObj") JSONObject jsonObj);
}
