package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.RoleData;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(RoleDataDao) 表数据库访问层
//generate by redcloud,2020-07-24 19:59:20
public interface RoleDataDao {
    //@param id 主键 
    //@return 实例对象RoleData 
    RoleData queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象RoleData 
    List<Map> roleDataQuery(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return JSONObject 
    void roleDataSetRelation(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象RoleData 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象RoleData 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
}
