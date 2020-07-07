package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.RolePage;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(RolePageDao) 表数据库访问层
//generate by redcloud,2020-07-08 01:57:14
public interface RolePageDao {
    //@param id 主键 
    //@return 实例对象RolePage 
    RolePage queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return JSONObject
    Integer rolePageMmjoin(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return JSONObject 
    Integer rolePageMmsub(@Param("jsonObj") JSONObject jsonObj);
}
