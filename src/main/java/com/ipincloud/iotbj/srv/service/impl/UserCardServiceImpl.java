package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.UserCard;
import com.ipincloud.iotbj.srv.dao.UserCardDao;
import com.ipincloud.iotbj.srv.service.UserCardService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(UserCard)用户卡片 服务实现类
//generate by redcloud,2020-07-07 10:18:16
@Service("UserCardService")
public class UserCardServiceImpl implements UserCardService {
    @Resource
    private UserCardDao userCardDao;
    //@param id 主键 
    //@return 实例对象UserCard 
    @Override 
    public UserCard queryById(Long id){
        this.userCardDao.queryById(id); 
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象UserCard 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.userCardDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询UserCard 分页 
    @Override
    public List<Map> userCardList(JSONObject jsonObj){

        int totalRec = this.countUserCardList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.userCardDao.userCardList(jsonObj)
        list<Map> retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
}
