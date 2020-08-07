package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.UserRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(UserRoleDao) 表数据库访问层
//generate by redcloud,2020-07-24 19:59:20
public interface UserRoleDao {
    //@param id 主键 
    //@return 实例对象UserRole 
    UserRole queryById(Long id);
    //@param jsonObj 过滤条件等 
    JSONObject queryByThirdUUID(String thirdUUID);
    //@return JSONObject
    Integer userRoleMmjoin(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return JSONObject 
    Integer userRoleMmsub(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象UserRole 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象UserRole 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);

    int deletesByThirdUUID(@Param("delThirdUUID") List<String>  delThirdUUID);
}
