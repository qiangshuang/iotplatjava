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
import com.ipincloud.iotbj.srv.domain.Accesslog;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.AccesslogService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Accesslog)访问记录 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("AccesslogService")
public class AccesslogServiceImpl implements AccesslogService {
    @Resource
    private AccesslogDao accesslogDao;

    //@param id 主键 
    //@return 实例对象Accesslog 
    @Override 
    public Accesslog queryById(Long id){
        return this.accesslogDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象Accesslog 查询
    @Override
    public List<Map> accesslogQuery(JSONObject jsonObj){

        return this.accesslogDao.accesslogQuery(jsonObj);
        
    }
    }
