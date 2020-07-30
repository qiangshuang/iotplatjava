package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Tags;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)标签管理 服务接口
//generate by redcloud,2020-07-24 19:59:20
public interface TagsService {
    //@param id 主键 
    //@return 实例对象Tags 
    Tags queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象Tags 
Map tagsList(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesTagsInst(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 实例对象Tags 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Tags 
void updateInst(JSONObject jsonObj);
}
