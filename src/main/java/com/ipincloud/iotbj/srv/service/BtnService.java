package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Btn;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-24 19:59:20
public interface BtnService {
    //@param id 主键 
    //@return 实例对象Btn 
    Btn queryById(Long id);
    //@param userId 用户ID 
    //@return 实例Btn列表 
    String queryByUserId(long userId);
//@param jsonObj 过滤条件查询 
//@return 实例对象Btn 
Map btnList(JSONObject jsonObj);
}
