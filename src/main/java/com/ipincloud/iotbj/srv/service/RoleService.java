package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Role;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)角色 服务接口
//generate by redcloud,2020-07-07 10:18:16
public interface RoleService {
//@param id 主键 
//@return 实例对象Role 
Role queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象Role 
List<Map> roleList(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象Role 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Role 
void updateInst(@Param("jsonObj") JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
//@param jsonObj 过滤条件等 
//@return 实例对象Role 
List<Map> roleQuery(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 无Role 
void roleUserRoleMmlist(JSONObject jsonObj);
}
