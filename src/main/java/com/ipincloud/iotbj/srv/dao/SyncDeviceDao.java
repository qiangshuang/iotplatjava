package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.SyncDevice;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(SyncDeviceDao)车闸设备同步 表数据库访问层
//generate by redcloud,2020-07-21 16:22:04
public interface SyncDeviceDao {
    //@param id 主键 
    //@return 实例对象SyncDevice 
    SyncDevice queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> syncDeviceList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countSyncDeviceList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象SyncDevice 
    int addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象SyncDevice 
    int updateInst(@Param("jsonObj") JSONObject jsonObj);
}
