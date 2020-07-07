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
import com.ipincloud.iotbj.srv.domain.Person;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.PersonService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Person) 服务实现类
//generate by redcloud,2020-07-08 01:57:14
@Service("PersonService")
public class PersonServiceImpl implements PersonService {
    @Resource
    private PersonDao personDao;

    //@param id 主键 
    //@return 实例对象Person 
    @Override 
    public Person queryById(Long id){
        return this.personDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Person 分页 
    @Override
    public Map personList(JSONObject jsonObj){

        int totalRec = this.personDao.countPersonList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.personDao.personList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
}
