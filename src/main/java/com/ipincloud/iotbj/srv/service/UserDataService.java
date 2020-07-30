package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.UserData;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-24 19:59:20
public interface UserDataService {
    //@param id 主键 
    //@return 实例对象UserData 
    UserData queryById(Long id);
//@param jsonObj 过滤条件等 
//@return 实例对象UserData 
List<Map> userDataQuery(JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return JSONObject 
    void userDataSetRelation(JSONObject jsonObj);
}
