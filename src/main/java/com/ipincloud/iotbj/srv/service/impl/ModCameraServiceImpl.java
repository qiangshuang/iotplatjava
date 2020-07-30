package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.ModCamera;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.ModCameraService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(ModCamera)摄像机 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("ModCameraService")
public class ModCameraServiceImpl implements ModCameraService {
    @Resource
    private ModCameraDao modCameraDao;

    //@param id 主键 
    //@return 实例对象ModCamera 
    @Override 
    public ModCamera queryById(Long id){
        return this.modCameraDao.queryById(id); 
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象ModCamera 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        jsonObj = ParaUtils.removeSurplusCol(jsonObj,"camera_index,firm_type,hik_camera_index,camera_name,capability_set,ptz,record_location,trans_type,status,region_path,channel_no,trans_ptz,hik_url");
        this.modCameraDao.addInst(jsonObj);
        return jsonObj; 
                
    } 
}
