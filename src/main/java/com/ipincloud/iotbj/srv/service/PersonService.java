package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Person;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-07 10:18:16
public interface PersonService {
//@param id 主键 
//@return 实例对象Person 
Person queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象Person 
List<Map> personList(JSONObject jsonObj);
}
