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
import com.ipincloud.iotbj.srv.domain.SensorStream;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.SensorStreamService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(SensorStream)数据流 服务实现类
//generate by redcloud,2020-07-24 16:15:21
@Service("SensorStreamService")
public class SensorStreamServiceImpl implements SensorStreamService {
    @Resource
    private SensorStreamDao sensorStreamDao;

    //@param id 主键 
    //@return 实例对象SensorStream 
    @Override 
    public SensorStream queryById(Long id){
        return this.sensorStreamDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询SensorStream 分页 
    @Override
    public Map sensorStreamList(JSONObject jsonObj){

        int totalRec = this.sensorStreamDao.countSensorStreamList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.sensorStreamDao.sensorStreamList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
}
