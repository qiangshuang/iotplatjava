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
import com.ipincloud.iotbj.srv.domain.Visitperson;
import com.ipincloud.iotbj.srv.dao.VisitpersonDao;
import com.ipincloud.iotbj.srv.service.VisitpersonService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Visitperson) 服务实现类
//generate by redcloud,2020-07-07 23:53:41
@Service("VisitpersonService")
public class VisitpersonServiceImpl implements VisitpersonService {
    @Resource
    private VisitpersonDao visitpersonDao;
    //@param id 主键 
    //@return 实例对象Visitperson 
    @Override 
    public Visitperson queryById(Long id){
        this.visitpersonDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Visitperson 分页 
    @Override
    public Map visitpersonList(JSONObject jsonObj){

        int totalRec = this.visitpersonDao.countVisitpersonList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.visitpersonDao.visitpersonList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 调用参数 
    //@return 实例对象Visitperson 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.visitpersonDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

}
