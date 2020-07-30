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
import com.ipincloud.iotbj.srv.domain.UserCard;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.UserCardService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(UserCard)用户卡片 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("UserCardService")
public class UserCardServiceImpl implements UserCardService {
    @Resource
    private UserCardDao userCardDao;

    //@param id 主键 
    //@return 实例对象UserCard 
    @Override 
    public UserCard queryById(Long id){
        return this.userCardDao.queryById(id); 
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象UserCard 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
            jsonObj = ParaUtils.removeSurplusCol(jsonObj,"id,cardno,user_name,state,user_id");
            this.userCardDao.addInst(jsonObj);
        // jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询UserCard 分页 
    @Override
    public Map userCardList(JSONObject jsonObj){

        int totalRec = this.userCardDao.countUserCardList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.userCardDao.userCardList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    }
