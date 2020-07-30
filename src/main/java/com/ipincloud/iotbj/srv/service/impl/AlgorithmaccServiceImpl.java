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
import com.ipincloud.iotbj.srv.domain.Algorithmacc;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.AlgorithmaccService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Algorithmacc)算法接入 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("AlgorithmaccService")
public class AlgorithmaccServiceImpl implements AlgorithmaccService {
    @Resource
    private AlgorithmaccDao algorithmaccDao;

    //@param id 主键 
    //@return 实例对象Algorithmacc 
    @Override 
    public Algorithmacc queryById(Long id){
        return this.algorithmaccDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Algorithmacc 分页 
    @Override
    public Map algorithmaccList(JSONObject jsonObj){

        int totalRec = this.algorithmaccDao.countAlgorithmaccList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.algorithmaccDao.algorithmaccList(jsonObj);
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
    public Integer deletesAlgorithmaccInst(JSONObject jsonObj){
        Integer delNum1 = this.algorithmaccDao.deletesInst(jsonObj); 
                return delNum1;
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Algorithmacc 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
            jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,address,accessmode,warning_level,relation_face,title,describion,accesscode");
            this.algorithmaccDao.addInst(jsonObj);
        // jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Algorithmacc 
    @Override 
    public void updateInst(JSONObject jsonObj){
        jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,address,accessmode,warning_level,relation_face,title,describion,accesscode");        this.algorithmaccDao.updateInst(jsonObj); 
    } 
}
