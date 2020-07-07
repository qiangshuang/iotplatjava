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
import com.ipincloud.iotbj.srv.domain.Proman;
import com.ipincloud.iotbj.srv.dao.PromanDao;
import com.ipincloud.iotbj.srv.service.PromanService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Proman)产品管理 服务实现类
//generate by redcloud,2020-07-07 23:53:41
@Service("PromanService")
public class PromanServiceImpl implements PromanService {
    @Resource
    private PromanDao promanDao;
    //@param id 主键 
    //@return 实例对象Proman 
    @Override 
    public Proman queryById(Long id){
        this.promanDao.queryById(id); 
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Proman 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.promanDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Proman 
    @Override 
    public void updateInst(JSONObject jsonObj){
        this.promanDao.updateInst(jsonObj); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Proman 分页 
    @Override
    public Map promanList(JSONObject jsonObj){

        int totalRec = this.promanDao.countPromanList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.promanDao.promanList(jsonObj);
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
    public Integer deletesPromanInst(JSONObject jsonObj){
        Integer delNum1 = this.promanDao.deletesInst(jsonObj); 
                return delNum1;
    } 

}
