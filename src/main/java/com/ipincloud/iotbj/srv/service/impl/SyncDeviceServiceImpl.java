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
import com.ipincloud.iotbj.srv.domain.SyncDevice;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.SyncDeviceService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(SyncDevice)车闸设备同步 服务实现类
//generate by redcloud,2020-07-21 16:22:04
@Service("SyncDeviceService")
public class SyncDeviceServiceImpl implements SyncDeviceService {
    @Resource
    private SyncDeviceDao syncDeviceDao;

    //@param id 主键 
    //@return 实例对象SyncDevice 
    @Override 
    public SyncDevice queryById(Long id){
        return this.syncDeviceDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询SyncDevice 分页 
    @Override
    public Map syncDeviceList(JSONObject jsonObj){

        int totalRec = this.syncDeviceDao.countSyncDeviceList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.syncDeviceDao.syncDeviceList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    }
