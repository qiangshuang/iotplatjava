package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Sensorcate;
import com.ipincloud.iotbj.srv.dao.SensorcateDao;
import com.ipincloud.iotbj.srv.service.SensorcateService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Sensorcate)传感器类型 服务实现类
//generate by redcloud,2020-07-07 10:18:16
@Service("SensorcateService")
public class SensorcateServiceImpl implements SensorcateService {
    @Resource
    private SensorcateDao sensorcateDao;
    //@param id 主键 
    //@return 实例对象Sensorcate 
    @Override 
    public Sensorcate queryById(Long id){
        this.sensorcateDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Sensorcate 分页 
    @Override
    public List<Map> sensorcateList(JSONObject jsonObj){

        int totalRec = this.countSensorcateList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.sensorcateDao.sensorcateList(jsonObj)
        list<Map> retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 调用参数 
    //@return 实例对象Sensorcate 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.sensorcateDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Sensorcate 
    @Override 
    public void updateInst(JSONObject jsonObj){
        return this.sensorcateDao.updateInst(jsonObj); 
    } 
    //@param jsonObj 调用参数 
    //@return 影响记录数%s 
    @Override 
    public Integer deleteInst(JSONObject jsonObj){
        return this.sensorcateDao.deleteInst(jsonObj); 
    } 

}
