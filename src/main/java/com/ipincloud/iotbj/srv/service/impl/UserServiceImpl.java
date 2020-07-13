package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.User;
import com.ipincloud.iotbj.srv.dao.UserDao;
import com.ipincloud.iotbj.srv.service.UserService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(User) 服务实现类
//generate by redcloud,2020-07-07 10:18:16
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    //@param id 主键 
    //@return 实例对象User 
    @Override 
    public User queryById(Long id){
        this.userDao.queryById(id); 
    } 
//@param userName 账号 
//@return 实例对象User 
    @Override 
public User queryByUsername(String userName){
        return this.userDao.queryByUsername(userName); 
    } 
    //已处理，查看服务:/useraccount 

    //@param jsonObj 调用参数 
    //@return 实例对象User 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        this.userDao.addInst(jsonObj);
        return jsonObj; 
                
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数User 
    @Override 
    public void updateInst(JSONObject jsonObj){
        return this.userDao.updateInst(jsonObj); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询User 分页 
    @Override
    public List<Map> userList(JSONObject jsonObj){

        int totalRec = this.countUserList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.userDao.userList(jsonObj)
        list<Map> retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 过滤条件等 
    //@return 对象查询User 分页 
    @Override
    public List<Map> userList(JSONObject jsonObj){

        int totalRec = this.countUserList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.userDao.userList(jsonObj)
        list<Map> retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 过滤条件等 
    //@return 对象查询User 分页 
    @Override
    public List<Map> userList(JSONObject jsonObj){

        int totalRec = this.countUserList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.userDao.userList(jsonObj)
        list<Map> retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 过滤条件等 
    //@return 对象连接查询User 分页 
    @Override
    public List<Map> userUserRoleListJoin(JSONObject jsonObj){

        int totalRec = this.countUserUserRoleListJoin(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.userDao.userUserRoleListJoin(jsonObj)
        list<Map> retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
}
