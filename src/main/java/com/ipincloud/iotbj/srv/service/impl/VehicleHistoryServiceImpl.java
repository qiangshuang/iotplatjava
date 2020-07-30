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
import com.ipincloud.iotbj.srv.domain.VehicleHistory;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.VehicleHistoryService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(VehicleHistory)车辆进出记录 服务实现类
//generate by redcloud,2020-07-24 19:59:21
@Service("VehicleHistoryService")
public class VehicleHistoryServiceImpl implements VehicleHistoryService {
    @Resource
    private VehicleHistoryDao vehicleHistoryDao;

    //@param id 主键 
    //@return 实例对象VehicleHistory 
    @Override 
    public VehicleHistory queryById(Long id){
        return this.vehicleHistoryDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询VehicleHistory 分页 
    @Override
    public Map vehicleHistoryList(JSONObject jsonObj){

        int totalRec = this.vehicleHistoryDao.countVehicleHistoryList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.vehicleHistoryDao.vehicleHistoryList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    }
