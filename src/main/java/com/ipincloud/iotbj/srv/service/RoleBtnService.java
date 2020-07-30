package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.RoleBtn;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-21 21:46:14
public interface RoleBtnService {
    //@param id 主键 
    //@return 实例对象RoleBtn 
    RoleBtn queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return JSONObject 
    void roleBtnSetRelation(JSONObject jsonObj);
}
