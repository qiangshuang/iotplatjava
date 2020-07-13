package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.UserRole;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-07 10:18:16
public interface UserRoleService {
//@param id 主键 
//@return 实例对象UserRole 
UserRole queryById(Long id);
    //@param jsonObj 新增数据等 
    //@return jsonObj 
    Integer userRoleMmjoin(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return JSONObject 
    Integer userRoleMmsub(@Param("jsonObj") JSONObject jsonObj);
}
