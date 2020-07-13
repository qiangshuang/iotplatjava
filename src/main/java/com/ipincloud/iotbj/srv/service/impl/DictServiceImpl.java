package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Dict;
import com.ipincloud.iotbj.srv.dao.DictDao;
import com.ipincloud.iotbj.srv.service.DictService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Dict) 服务实现类
//generate by redcloud,2020-07-07 10:18:15
@Service("DictService")
public class DictServiceImpl implements DictService {
    @Resource
    private DictDao dictDao;
    //@param id 主键 
    //@return 实例对象Dict 
    @Override 
    public Dict queryById(Long id){
        this.dictDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Dict 分页 
    @Override
    public List<Map> dictList(JSONObject jsonObj){

        int totalRec = this.countDictList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.dictDao.dictList(jsonObj)
        list<Map> retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
}
