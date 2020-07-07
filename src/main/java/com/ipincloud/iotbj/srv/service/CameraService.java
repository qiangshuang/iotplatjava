package com.ipincloud.iotbj.srv.service;
import com.ipincloud.iotbj.srv.domain.Camera;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
//(Iotbj)摄像机 服务接口
//generate by redcloud,2020-07-08 01:57:14
public interface CameraService {
    //@param id 主键 
    //@return 实例对象Camera 
    Camera queryById(Long id);
//@param jsonObj 调用参数 
//@return 实例对象Camera 
JSONObject addInst( JSONObject jsonObj);
//@param jsonObj 调用参数  
//@return 影响记录数 
Integer deletesCameraInst(JSONObject jsonObj);
//@param jsonObj 过滤条件查询 
//@return 实例对象Camera 
Map cameraList(JSONObject jsonObj);
//@param jsonObj 调用参数 
//@return 影响记录数Camera 
void updateInst(JSONObject jsonObj);
    //设备相关接口，参看api接口/deviceopen
    //设备相关接口，参看api接口/devicerestart
    //设备相关接口，参看api接口/devicesync
    //设备相关接口，参看api接口/deviceclose
}
