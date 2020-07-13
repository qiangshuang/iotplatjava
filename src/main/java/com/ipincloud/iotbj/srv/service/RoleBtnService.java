package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.RoleBtn;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-07 10:18:16
public interface RoleBtnService {
//@param id 主键 
//@return 实例对象RoleBtn 
RoleBtn queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return JSONObject 
    void roleBtnSetRelation(@Param("jsonObj") JSONObject jsonObj);
}
