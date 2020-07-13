package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Region;
import com.ipincloud.iotbj.srv.dao.RegionDao;
import com.ipincloud.iotbj.srv.service.RegionService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Region)区域 服务实现类
//generate by redcloud,2020-07-07 10:18:16
@Service("RegionService")
public class RegionServiceImpl implements RegionService {
    @Resource
    private RegionDao regionDao;
    //@param id 主键 
    //@return 实例对象Region 
    @Override 
    public Region queryById(Long id){
        this.regionDao.queryById(id); 
    } 

    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer deletesInst(JSONObject jsonObj){
        Integer delNum1 = this.regionDao.deletesInst(jsonObj); 
        Integer delNum2 = new com.ipincloud.iotbj.srv.dao.Dao().deletesInst(jsonObj); 
        return delNum1+delNum2;
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象Region 树形查询
    @Override
    public List<Map> regionTree(JSONObject jsonObj){

        if (ParaUtils.notHaveColVal(jsonObj,"parent_id") != nil && ParaUtils.notHaveColVal(jsonObj,"parent_id").length() > 0){
            return this.regionDao.queryTreeHp(jsonObj);
        }else{
            return this.regionDao.queryTreeNp(jsonObj);
        }
    }
            
            
    //@param jsonObj 调用参数 
    //@return 实例对象Region 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.regionDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Region 
    @Override 
    public void updateInst(JSONObject jsonObj){
        return this.regionDao.updateInst(jsonObj); 
    } 

}
