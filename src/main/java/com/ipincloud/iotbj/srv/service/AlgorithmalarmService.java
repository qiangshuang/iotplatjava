package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Algorithmalarm;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)报警记录 服务接口
//generate by redcloud,2020-07-24 19:59:20
public interface AlgorithmalarmService {
    //@param id 主键 
    //@return 实例对象Algorithmalarm 
    Algorithmalarm queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象Algorithmalarm 
Map algorithmalarmList(JSONObject jsonObj);
// xxxxxxxxxxxxxxx 
// 没有处理的BtnMap:报警记录确认,/confirmState,confirmState 
// xxxxxxxxxxxxxxx 
// xxxxxxxxxxxxxxx 
// 没有处理的BtnMap:报警记录误报,/misstate,misstate 
// xxxxxxxxxxxxxxx 
}
