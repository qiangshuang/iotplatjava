package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Proman;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)产品管理 服务接口
//generate by redcloud,2020-07-07 23:53:41
public interface PromanService {
    //@param id 主键 
    //@return 实例对象Proman 
    Proman queryById(Long id);
//@param jsonObj 调用参数 
//@return 实例对象Proman 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Proman 
void updateInst(JSONObject jsonObj);
//@param jsonObj 过滤条件查询 
//@return 实例对象Proman 
Map promanList(JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesPromanInst(JSONObject jsonObj);
}
