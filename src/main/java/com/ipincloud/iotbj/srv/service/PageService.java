package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Page;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj) 服务接口
//generate by redcloud,2020-07-24 19:59:20
public interface PageService {
    //@param id 主键 
    //@return 实例对象Page 
    Page queryById(Long id);
    //@param userId 用户ID 
    //@return PageMap列表 
    List<Map> queryAllByUser( long userId);
//@param jsonObj 调用参数  
//@return 无Page 
Map pageRolePageMmlist(JSONObject jsonObj);
}
