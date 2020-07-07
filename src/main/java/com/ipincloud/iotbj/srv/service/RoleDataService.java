package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.RoleData;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-08 01:57:14
public interface RoleDataService {
    //@param id 主键 
    //@return 实例对象RoleData 
    RoleData queryById(Long id);
//@param jsonObj 过滤条件等 
//@return 实例对象RoleData 
List<Map> roleDataQuery(JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return JSONObject 
    void roleDataSetRelation(JSONObject jsonObj);
}
