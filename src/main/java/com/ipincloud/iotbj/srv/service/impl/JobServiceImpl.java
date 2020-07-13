package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Job;
import com.ipincloud.iotbj.srv.dao.JobDao;
import com.ipincloud.iotbj.srv.service.JobService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Job) 服务实现类
//generate by redcloud,2020-07-07 10:18:16
@Service("JobService")
public class JobServiceImpl implements JobService {
    @Resource
    private JobDao jobDao;
    //@param id 主键 
    //@return 实例对象Job 
    @Override 
    public Job queryById(Long id){
        this.jobDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Job 分页 
    @Override
    public List<Map> jobList(JSONObject jsonObj){

        int totalRec = this.countJobList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.jobDao.jobList(jsonObj)
        list<Map> retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 调用参数 
    //@return 实例对象Job 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.jobDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Job 
    @Override 
    public void updateInst(JSONObject jsonObj){
        return this.jobDao.updateInst(jsonObj); 
    } 
    //@param jsonObj 调用参数 
    //@return 影响记录数%s 
    @Override 
    public Integer deleteInst(JSONObject jsonObj){
        return this.jobDao.deleteInst(jsonObj); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Job 分页 
    @Override
    public List<Map> jobList(JSONObject jsonObj){

        int totalRec = this.countJobList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.jobDao.jobList(jsonObj)
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
        Integer delNum1 = this.jobDao.deletesInst(jsonObj); 
        Integer delNum2 = new com.ipincloud.iotbj.srv.dao.Dao().deletesInst(jsonObj); 
        return delNum1+delNum2;
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Job 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.jobDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Job 
    @Override 
    public void updateInst(JSONObject jsonObj){
        return this.jobDao.updateInst(jsonObj); 
    } 

}
