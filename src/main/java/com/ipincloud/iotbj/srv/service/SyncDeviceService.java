package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.SyncDevice;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)车闸设备同步 服务接口
//generate by redcloud,2020-07-21 16:22:04
public interface SyncDeviceService {
    //@param id 主键 
    //@return 实例对象SyncDevice 
    SyncDevice queryById(Long id);
//@param jsonObj 过滤条件查询 
//@return 实例对象SyncDevice 
Map syncDeviceList(JSONObject jsonObj);
}
