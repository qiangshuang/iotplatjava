package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Algorithmacc;
import com.ipincloud.iotbj.srv.dao.AlgorithmaccDao;
import com.ipincloud.iotbj.srv.service.AlgorithmaccService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Algorithmacc)算法接入 服务实现类
//generate by redcloud,2020-07-07 10:18:15
@Service("AlgorithmaccService")
public class AlgorithmaccServiceImpl implements AlgorithmaccService {
    @Resource
    private AlgorithmaccDao algorithmaccDao;
    //@param id 主键 
    //@return 实例对象Algorithmacc 
    @Override 
    public Algorithmacc queryById(Long id){
        this.algorithmaccDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Algorithmacc 分页 
    @Override
    public List<Map> algorithmaccList(JSONObject jsonObj){

        int totalRec = this.countAlgorithmaccList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.algorithmaccDao.algorithmaccList(jsonObj)
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
        Integer delNum1 = this.algorithmaccDao.deletesInst(jsonObj); 
        Integer delNum2 = new com.ipincloud.iotbj.srv.dao.Dao().deletesInst(jsonObj); 
        return delNum1+delNum2;
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Algorithmacc 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.algorithmaccDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Algorithmacc 
    @Override 
    public void updateInst(JSONObject jsonObj){
        return this.algorithmaccDao.updateInst(jsonObj); 
    } 

}
