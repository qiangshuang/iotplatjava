package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Accesslog;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(AccesslogDao)访问记录 表数据库访问层
//generate by redcloud,2020-07-08 01:57:14
public interface AccesslogDao {
    //@param id 主键 
    //@return 实例对象Accesslog 
    Accesslog queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> accesslogList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countAccesslogList(@Param("jsonObj") JSONObject jsonObj);
}
