package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Algorithm;
import com.ipincloud.iotbj.srv.dao.AlgorithmDao;
import com.ipincloud.iotbj.srv.service.AlgorithmService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Algorithm)算法 服务实现类
//generate by redcloud,2020-07-07 10:18:15
@Service("AlgorithmService")
public class AlgorithmServiceImpl implements AlgorithmService {
    @Resource
    private AlgorithmDao algorithmDao;
    //@param id 主键 
    //@return 实例对象Algorithm 
    @Override 
    public Algorithm queryById(Long id){
        this.algorithmDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Algorithm 分页 
    @Override
    public List<Map> algorithmList(JSONObject jsonObj){

        int totalRec = this.countAlgorithmList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.algorithmDao.algorithmList(jsonObj)
        list<Map> retMap = new HashMap();
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
    public Integer deletesInst(JSONObject jsonObj){
        Integer delNum1 = this.algorithmDao.deletesInst(jsonObj); 
        Integer delNum2 = new com.ipincloud.iotbj.srv.dao.Dao().deletesInst(jsonObj); 
        return delNum1+delNum2;
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Algorithm 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.algorithmDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Algorithm 
    @Override 
    public void updateInst(JSONObject jsonObj){
        return this.algorithmDao.updateInst(jsonObj); 
    } 
    //设备相关接口，参看api接口/algorithmopen
    //设备相关接口，参看api接口/algorithmclose
    //设备相关接口，参看api接口/algorithmrestart

}
