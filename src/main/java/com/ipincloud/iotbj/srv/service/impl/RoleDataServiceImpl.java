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
import com.ipincloud.iotbj.srv.domain.RoleData;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.RoleDataService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(RoleData) 服务实现类
//generate by redcloud,2020-07-08 01:57:14
@Service("RoleDataService")
public class RoleDataServiceImpl implements RoleDataService {
    @Resource
    private RoleDataDao roleDataDao;

    //@param id 主键 
    //@return 实例对象RoleData 
    @Override 
    public RoleData queryById(Long id){
        return this.roleDataDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象RoleData 查询
    @Override
    public List<Map> roleDataQuery(JSONObject jsonObj){

        return this.roleDataDao.roleDataQuery(jsonObj);
        
    }
        //@param jsonObj 过滤条件等 
    //@return JSONObject 
    public void roleDataSetRelation(JSONObject jsonObj){
        this.roleDataDao.roleDataSetRelation(jsonObj); 
    } 

}
