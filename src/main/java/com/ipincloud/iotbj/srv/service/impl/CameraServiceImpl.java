package com.ipincloud.iotbj.srv.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.dao.CameraDao;
import com.ipincloud.iotbj.srv.domain.Camera;
import com.ipincloud.iotbj.srv.service.CameraService;
import com.ipincloud.iotbj.utils.ParaUtils;
import com.ipincloud.iotbj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//(Camera)摄像机 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("CameraService")
public class CameraServiceImpl implements CameraService {
    @Resource
    private CameraDao cameraDao;
	
	@Value("${algorithm.url}")
    String algorithmUrl;

    //@param id 主键 
    //@return 实例对象Camera 
    @Override 
    public Camera queryById(Long id){
        return this.cameraDao.queryById(id); 
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Camera 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        String pushaddress = "";
        if (StringUtils.isNotEmpty(algorithmUrl) && StringUtils.isNotEmpty(jsonObj.getString("cameraIndex"))) {
            pushaddress = "http://" + algorithmUrl + ":8000/live/" + jsonObj.getString("cameraIndex") + ".flv";
        } else {
            pushaddress = jsonObj.getString("videoa");
        }
        jsonObj.put("pushaddress", pushaddress);
            jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,cameraIndex,title,region_id,region_title,pos,resolution,url,codec,scene,framerate,corp,videoa,state,algorithms,planview,algorithms_id,pushaddress");
            this.cameraDao.addInst(jsonObj);
        // jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer deletesCameraInst(JSONObject jsonObj){
        Integer delNum1 = this.cameraDao.deletesInst(jsonObj); 
                return delNum1;
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Camera 分页 
    @Override
    public Map cameraList(JSONObject jsonObj){

        int totalRec = this.cameraDao.countCameraList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.cameraDao.cameraList(jsonObj);
        Map retMap = new HashMap();
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
        jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,cameraIndex,title,region_id,region_title,pos,resolution,url,codec,scene,framerate,corp,videoa,state,algorithms,planview,algorithms_id,pushaddress");        this.cameraDao.updateInst(jsonObj); 
    } 
    //设备相关接口，参看api接口/deviceopen
    //设备相关接口，参看api接口/devicerestart
    //设备相关接口，参看api接口/devicesync
    //设备相关接口，参看api接口/deviceclose
}
