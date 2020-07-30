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
import com.ipincloud.iotbj.srv.domain.VehicleGate;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.VehicleGateService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(VehicleGate)车辆闸机 服务实现类
//generate by redcloud,2020-07-24 19:59:21
@Service("VehicleGateService")
public class VehicleGateServiceImpl implements VehicleGateService {
    @Resource
    private VehicleGateDao vehicleGateDao;

    //@param id 主键 
    //@return 实例对象VehicleGate 
    @Override 
    public VehicleGate queryById(Long id){
        return this.vehicleGateDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询VehicleGate 分页 
    @Override
    public Map vehicleGateList(JSONObject jsonObj){

        int totalRec = this.vehicleGateDao.countVehicleGateList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.vehicleGateDao.vehicleGateList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer deletesVehicleGateInst(JSONObject jsonObj){
        Integer delNum1 = this.vehicleGateDao.deletesInst(jsonObj); 
                return delNum1;
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数VehicleGate 
    @Override 
    public void updateInst(JSONObject jsonObj){
        jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,title,firm,created,updated,indexCode,state");        this.vehicleGateDao.updateInst(jsonObj); 
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象VehicleGate 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
            jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,title,firm,created,updated,indexCode,state");
            this.vehicleGateDao.addInst(jsonObj);
        // jsonObj.put("id",genId);
        return jsonObj;
            
    } 
}
