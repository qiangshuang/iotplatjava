package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.RolePage;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-07 23:53:41
public interface RolePageService {
    //@param id 主键 
    //@return 实例对象RolePage 
    RolePage queryById(Long id);
    //@param jsonObj 新增数据等 
    //@return jsonObj 
    Integer rolePageMmjoin(JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return JSONObject 
    Integer rolePageMmsub(JSONObject jsonObj);
}
