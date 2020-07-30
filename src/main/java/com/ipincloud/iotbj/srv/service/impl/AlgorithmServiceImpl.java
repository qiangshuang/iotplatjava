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
import com.ipincloud.iotbj.srv.domain.Algorithm;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.AlgorithmService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Algorithm)算法 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("AlgorithmService")
public class AlgorithmServiceImpl implements AlgorithmService {
    @Resource
    private AlgorithmDao algorithmDao;

    //@param id 主键 
    //@return 实例对象Algorithm 
    @Override 
    public Algorithm queryById(Long id){
        return this.algorithmDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Algorithm 分页 
    @Override
    public Map algorithmList(JSONObject jsonObj){

        int totalRec = this.algorithmDao.countAlgorithmList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.algorithmDao.algorithmList(jsonObj);
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
    public Integer deletesAlgorithmInst(JSONObject jsonObj){
        Integer delNum1 = this.algorithmDao.deletesInst(jsonObj); 
                return delNum1;
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Algorithm 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
            jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,camera_id,algorithm_id,pushtime_start,pushtime_end,algorithm_title,camera_title,border,direction,distinguish,state,framerate,resolution,videoa,codec,algorithm_url,user_ids,user_titles,opentime");
            this.algorithmDao.addInst(jsonObj);
        // jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Algorithm 
    @Override 
    public void updateInst(JSONObject jsonObj){
        jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,camera_id,algorithm_id,pushtime_start,pushtime_end,algorithm_title,camera_title,border,direction,distinguish,state,framerate,resolution,videoa,codec,algorithm_url,user_ids,user_titles,opentime");        this.algorithmDao.updateInst(jsonObj); 
    } 
    //设备相关接口，参看api接口/algorithmopen
    //设备相关接口，参看api接口/algorithmclose
    //设备相关接口，参看api接口/algorithmrestart
}
