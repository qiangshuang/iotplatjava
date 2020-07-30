package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Accesslog;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)访问记录 服务接口
//generate by redcloud,2020-07-24 19:59:20
public interface AccesslogService {
    //@param id 主键 
    //@return 实例对象Accesslog 
    Accesslog queryById(Long id);
//@param jsonObj 过滤条件等 
//@return 实例对象Accesslog 
List<Map> accesslogQuery(JSONObject jsonObj);
}
