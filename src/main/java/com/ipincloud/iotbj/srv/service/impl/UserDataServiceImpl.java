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
import com.ipincloud.iotbj.srv.domain.UserData;
import com.ipincloud.iotbj.srv.dao.UserDataDao;
import com.ipincloud.iotbj.srv.service.UserDataService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(UserData) 服务实现类
//generate by redcloud,2020-07-07 23:53:41
@Service("UserDataService")
public class UserDataServiceImpl implements UserDataService {
    @Resource
    private UserDataDao userDataDao;
    //@param id 主键 
    //@return 实例对象UserData 
    @Override 
    public UserData queryById(Long id){
        this.userDataDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象UserData 查询
    @Override
    public List<Map> userDataQuery(JSONObject jsonObj){

        return this.userDataDao.userDataQuery(jsonObj);
        
    }
        //@param jsonObj 过滤条件等 
    //@return JSONObject 
    public void userDataSetRelation(JSONObject jsonObj){
        return this.userDataDao.userDataSetRelation(jsonObj); 
    } 

}
