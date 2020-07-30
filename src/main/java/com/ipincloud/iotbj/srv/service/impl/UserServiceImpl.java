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
import com.ipincloud.iotbj.srv.domain.User;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.UserService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(User) 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    //@param id 主键 
    //@return 实例对象User 
    @Override 
    public User queryById(Long id){
        return this.userDao.queryById(id); 
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
        
        jsonObj = ParaUtils.removeSurplusCol(jsonObj,"mobile,user_name,pwd,id,title,parent_id,parent_title,lastlogin,job_title,job_id,thirdin,created,updated,photo,idnumber,jobno,cardnumber,mcardno,gender,personId");
        this.userDao.addInst(jsonObj);
        return jsonObj; 
                
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数User 
    @Override 
    public void updateInst(JSONObject jsonObj){
        jsonObj = ParaUtils.removeSurplusCol(jsonObj,"mobile,user_name,pwd,id,title,parent_id,parent_title,lastlogin,job_title,job_id,thirdin,created,updated,photo,idnumber,jobno,cardnumber,mcardno,gender,personId");        this.userDao.updateInst(jsonObj); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询User 分页 
    @Override
    public Map userList(JSONObject jsonObj){

        int totalRec = this.userDao.countUserList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.userDao.userList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 过滤条件等 
    //@return 对象连接查询User 分页 
    @Override
    public Map userUserRoleListJoin(JSONObject jsonObj){

        int totalRec = this.userDao.countUserUserRoleListJoin(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.userDao.userUserRoleListJoin(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    }
