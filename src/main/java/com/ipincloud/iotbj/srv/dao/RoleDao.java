package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Role;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(RoleDao)角色 表数据库访问层
//generate by redcloud,2020-07-07 23:53:41
public interface RoleDao {
    //@param id 主键 
    //@return 实例对象Role 
    Role queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> roleList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countRoleList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Role 
    Long addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Role 
    void updateInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Role 
    List<Map> roleQuery(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Role 
    List<Map> roleUserRoleMmlist(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数 
    Integer countRoleUserRoleMmlist(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
}
