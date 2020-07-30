package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Visitperson;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-24 19:59:21
public interface VisitpersonService {
    //@param id 主键 
    //@return 实例对象Visitperson 
    Visitperson queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象Visitperson 
Map visitpersonList(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象Visitperson 
JSONObject addInst( JSONObject jsonObj);
}
