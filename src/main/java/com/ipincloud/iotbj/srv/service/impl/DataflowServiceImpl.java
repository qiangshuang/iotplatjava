package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Dataflow;
import com.ipincloud.iotbj.srv.dao.DataflowDao;
import com.ipincloud.iotbj.srv.service.DataflowService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Dataflow)数据流 服务实现类
//generate by redcloud,2020-07-07 10:18:15
@Service("DataflowService")
public class DataflowServiceImpl implements DataflowService {
    @Resource
    private DataflowDao dataflowDao;
    //@param id 主键 
    //@return 实例对象Dataflow 
    @Override 
    public Dataflow queryById(Long id){
        this.dataflowDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Dataflow 分页 
    @Override
    public List<Map> dataflowList(JSONObject jsonObj){

        int totalRec = this.countDataflowList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.dataflowDao.dataflowList(jsonObj)
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
        Integer delNum1 = this.dataflowDao.deletesInst(jsonObj); 
        Integer delNum2 = new com.ipincloud.iotbj.srv.dao.Dao().deletesInst(jsonObj); 
        return delNum1+delNum2;
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Dataflow 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.dataflowDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Dataflow 
    @Override 
    public void updateInst(JSONObject jsonObj){
        return this.dataflowDao.updateInst(jsonObj); 
    } 

}
