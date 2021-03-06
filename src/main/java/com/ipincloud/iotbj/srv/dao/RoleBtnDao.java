package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.RoleBtn;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(RoleBtnDao) 表数据库访问层
//generate by redcloud,2020-07-21 21:46:14
public interface RoleBtnDao {
    //@param id 主键 
    //@return 实例对象RoleBtn 
    RoleBtn queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return JSONObject 
    void roleBtnSetRelation(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象RoleBtn 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象RoleBtn 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
}
