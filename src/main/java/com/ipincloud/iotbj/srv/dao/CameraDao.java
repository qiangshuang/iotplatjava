package com.ipincloud.iotbj.srv.dao;
import com.ipincloud.iotbj.srv.domain.Camera;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(CameraDao)摄像机 表数据库访问层
//generate by redcloud,2020-07-07 23:53:41
public interface CameraDao {
    //@param id 主键 
    //@return 实例对象Camera 
    Camera queryById(Long id);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Camera 
    Long addInst(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象 
    List<Map> cameraList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 总条数list 
    Integer countCameraList(@Param("jsonObj") JSONObject jsonObj);
    //@param jsonObj 过滤条件等 
    //@return 实例对象Camera 
    void updateInst(@Param("jsonObj") JSONObject jsonObj);
    //设备相关接口，参看api接口/deviceopen
    //设备相关接口，参看api接口/devicerestart
    //设备相关接口，参看api接口/devicesync
    //设备相关接口，参看api接口/deviceclose
    //@param jsonObj 过滤条件等 
    //@return 影响记录数 
    Integer deletesInst(@Param("jsonObj") JSONObject jsonObj);
}
