package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Briman;
import com.ipincloud.iotbj.srv.dao.BrimanDao;
import com.ipincloud.iotbj.srv.service.BrimanService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Briman)网桥管理 服务实现类
//generate by redcloud,2020-07-07 10:18:15
@Service("BrimanService")
public class BrimanServiceImpl implements BrimanService {
    @Resource
    private BrimanDao brimanDao;
    //@param id 主键 
    //@return 实例对象Briman 
    @Override 
    public Briman queryById(Long id){
        this.brimanDao.queryById(id); 
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Briman 
    @Override 
    public void updateInst(JSONObject jsonObj){
        return this.brimanDao.updateInst(jsonObj); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Briman 分页 
    @Override
    public List<Map> brimanList(JSONObject jsonObj){

        int totalRec = this.countBrimanList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.brimanDao.brimanList(jsonObj)
        list<Map> retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 调用参数 
    //@return 实例对象Briman 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.brimanDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer deletesInst(JSONObject jsonObj){
        Integer delNum1 = this.brimanDao.deletesInst(jsonObj); 
        Integer delNum2 = new com.ipincloud.iotbj.srv.dao.Dao().deletesInst(jsonObj); 
        return delNum1+delNum2;
    } 

}
