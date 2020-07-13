package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Org;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-07 10:18:16
public interface OrgService {
//@param id 主键 
//@return 实例对象Org 
Org queryById(Long id);
//@param jsonObj 过滤条件等 
//@return 实例对象Org 
List<Map> orgTree(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象Org 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Org 
void updateInst(@Param("jsonObj") JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
//@param jsonObj 过滤条件等 
//@return 实例对象Org 
List<Map> orgQuery(JSONObject jsonObj);
//@param jsonObj 过滤条件等 
//@return 实例对象Org 
List<Map> orgTree(JSONObject jsonObj);
//@param jsonObj 过滤条件查询 
//@return 实例对象Org 
List<Map> orgList(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return JSONObject 
JSONObject addOrgUserAttr(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 无 
void updateOrgUserInstAttr( JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
}
