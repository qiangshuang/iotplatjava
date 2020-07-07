package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Org;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-07 23:53:41
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
void updateInst(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesOrgInst(JSONObject jsonObj);
//@param jsonObj 过滤条件等 
//@return 实例对象Org 
List<Map> orgQuery(JSONObject jsonObj);
//@param jsonObj 过滤条件查询 
//@return 实例对象Org 
Map orgList(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return JSONObject 
JSONObject addOrgUserInstAttr(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 无 
void updateOrgUserInstAttr( JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesOrgUserUserRoleInst(JSONObject jsonObj);
}
