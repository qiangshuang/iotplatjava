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
import com.ipincloud.iotbj.srv.domain.Algorithmalarm;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.AlgorithmalarmService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Algorithmalarm)报警记录 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("AlgorithmalarmService")
public class AlgorithmalarmServiceImpl implements AlgorithmalarmService {
    @Resource
    private AlgorithmalarmDao algorithmalarmDao;

    //@param id 主键 
    //@return 实例对象Algorithmalarm 
    @Override 
    public Algorithmalarm queryById(Long id){
        return this.algorithmalarmDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Algorithmalarm 分页 
    @Override
    public Map algorithmalarmList(JSONObject jsonObj){

        int totalRec = this.algorithmalarmDao.countAlgorithmalarmList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.algorithmalarmDao.algorithmalarmList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    // xxxxxxxxxxxxxxx 
// 没有处理的BtnMap:报警记录确认,/confirmState,confirmState 
// xxxxxxxxxxxxxxx 
// xxxxxxxxxxxxxxx 
// 没有处理的BtnMap:报警记录误报,/misstate,misstate 
// xxxxxxxxxxxxxxx 
}
