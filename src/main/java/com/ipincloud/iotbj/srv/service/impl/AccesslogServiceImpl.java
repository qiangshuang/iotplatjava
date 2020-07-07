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
import com.ipincloud.iotbj.srv.domain.Accesslog;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.AccesslogService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Accesslog)访问记录 服务实现类
//generate by redcloud,2020-07-08 01:57:14
@Service("AccesslogService")
public class AccesslogServiceImpl implements AccesslogService {
    @Resource
    private AccesslogDao accesslogDao;

    //@param id 主键 
    //@return 实例对象Accesslog 
    @Override 
    public Accesslog queryById(Long id){
        return this.accesslogDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Accesslog 分页 
    @Override
    public Map accesslogList(JSONObject jsonObj){

        int totalRec = this.accesslogDao.countAccesslogList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.accesslogDao.accesslogList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
}
