package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.RoleBtn;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(RoleBtnDao) 表数据库访问层
//generate by redcloud,2020-07-07 23:53:41
public interface RoleBtnDao {
    //@param id 主键 
    //@return 实例对象RoleBtn 
    RoleBtn queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return JSONObject 
    void roleBtnSetRelation(@Param("jsonObj") JSONObject jsonObj);
}
