package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Access;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)门禁编辑 服务接口
//generate by redcloud,2020-07-08 01:57:14
public interface AccessService {
    //@param id 主键 
    //@return 实例对象Access 
    Access queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Access 
    Map accessGetAttr(JSONObject jsonObj);
}
