package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Camera;
import com.ipincloud.iotbj.srv.dao.CameraDao;
import com.ipincloud.iotbj.srv.service.CameraService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Camera)摄像机 服务实现类
//generate by redcloud,2020-07-07 10:18:15
@Service("CameraService")
public class CameraServiceImpl implements CameraService {
    @Resource
    private CameraDao cameraDao;
    //@param id 主键 
    //@return 实例对象Camera 
    @Override 
    public Camera queryById(Long id){
        this.cameraDao.queryById(id); 
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Camera 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.cameraDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer deletesInst(JSONObject jsonObj){
        Integer delNum1 = this.cameraDao.deletesInst(jsonObj); 
        Integer delNum2 = new com.ipincloud.iotbj.srv.dao.Dao().deletesInst(jsonObj); 
        return delNum1+delNum2;
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Camera 分页 
    @Override
    public List<Map> cameraList(JSONObject jsonObj){

        int totalRec = this.countCameraList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.cameraDao.cameraList(jsonObj)
        list<Map> retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 调用参数 
    //@return 影响记录数Camera 
    @Override 
    public void updateInst(JSONObject jsonObj){
        return this.cameraDao.updateInst(jsonObj); 
    } 
    //设备相关接口，参看api接口/deviceopen
    //设备相关接口，参看api接口/devicerestart
    //设备相关接口，参看api接口/devicesync
    //设备相关接口，参看api接口/deviceclose

}
