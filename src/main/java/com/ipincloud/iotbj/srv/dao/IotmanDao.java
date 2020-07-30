package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Iotman;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(IotmanDao)网关管理 表数据库访问层
//generate by redcloud,2020-07-24 19:59:20
public interface IotmanDao {
    //@param id 主键 
    //@return 实例对象Iotman 
    Iotman queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Iotman 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Iotman 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> iotmanList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countIotmanList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
}
